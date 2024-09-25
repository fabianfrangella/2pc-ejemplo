package ar.edu.unq.spring.distributed.orchestrator.service;

import ar.edu.unq.unidad3.dto.ItemDTO;
import ar.edu.unq.unidad3.dto.PersonajeDTO;
import ar.edu.unq.unidad3.dto.PublicacionDTO;
import ar.edu.unq.unidad3.modelo.Personaje;
import ar.edu.unq.unidad3.modelo.Publicacion;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


@Service
@AllArgsConstructor
public class TransactionCoordinatorService {
    private final PersonajeService personajeService;
    private final StoreService storeService;
    private final ItemService itemService;

    private static final Logger logger = LoggerFactory.getLogger(TransactionCoordinatorService.class);

    public Long onComprarEvent(Long compradorId, Long publicacionId) {
        var publicacion = storeService.findById(publicacionId).block();
        if (compradorId.equals(publicacion.getVendedorId())) {
            logger.info("Compra de la publicacion {} fallo porque el vendedor es el mismo que el comprador", publicacionId);
            throw new RuntimeException("El comprador no puede ser el mismo que el vendedor");
        }
        storeService.pausarPublicacion(publicacionId, compradorId).subscribe();

        return publicacionId;
    }

    public Result onPublicacionPausada(Long compradorId, Long publicacionId) {
        try {
            var publicacion = storeService.findById(publicacionId).block();
            itemService.cambiarOwner(publicacion.getItemId(), compradorId).block();

            if (publicacion.getEstado() == PublicacionDTO.Estado.INACTIVA) {
                logger.info("Compra de la publicacion {} fallo porque la publicacion esta inactiva", publicacionId);
                return Result.FAIL;
            }

            return Result.SUCCESS;
        } catch(Exception e) {
            return Result.FAIL;
        }
    }

    public Publicacion onPublicarEvent(Long vendedorId, Long itemId, BigDecimal precio) throws JsonProcessingException {
        var item = itemService.findById(itemId).doOnSuccess((itemDTO) -> {
            if (!itemDTO.getOwnerId().equals(vendedorId)) {
                throw new RuntimeException("El Item debe pertenecer al vendedor");
            }
        }).block();

        var vendedor = personajeService.findPersonaje(vendedorId).block();


        var publicacionBody = StoreService.PublicacionBody.builder()
                .personajeId(vendedorId)
                .itemId(itemId)
                .precio(precio)
                .build();

        var publicacion = storeService.publicar(publicacionBody).block();
        var personajeModel = vendedor.toModel();

        var itemModel = item.toModel(personajeModel);

        return publicacion.toModel(personajeModel, itemModel);
    }

    public PersonajeDTO crearPersonaje(PersonajeDTO personaje) throws JsonProcessingException {
        return personajeService.crearPersonaje(personaje).block();
    }

    public ItemDTO crearItem(ItemDTO item) throws JsonProcessingException {
        return itemService.crearItem(item).block();
    }

    public Personaje getPersonaje(Long personajeId) {
        var personajeDTO = personajeService.findPersonaje(personajeId).block();
        var itemsDTO = itemService.findByOwnerId(personajeId);
        var inventario = new HashSet<>(Objects.requireNonNull(itemsDTO.map(itemDTO -> itemDTO.toModel())
                .collectList()
                .block()));

        return personajeDTO.toModel(inventario);
    }

    public Publicacion findPublicacionById(Long publicacionId) {
        var publicacionDto = storeService.findById(publicacionId).block();
        var vendedor = getPersonaje(publicacionDto.getVendedorId());
        var itemDto = itemService.findById(publicacionDto.getItemId()).block();
        return publicacionDto.toModel(vendedor, itemDto.toModel(vendedor));
    }

    public List<Publicacion> findPublicacionesActivas() {
        var publicacionesDTO = storeService.findPublicacionesActivas().collectList().block();
        return publicacionesDTO.stream().map(dto -> {
            var vendedor = getPersonaje(dto.getVendedorId());
            var item = itemService.findById(dto.getItemId()).block().toModel();
            return dto.toModel(vendedor, item);
        }).toList();
    }

    public enum Result {
        SUCCESS, FAIL;
    }
}
