package ar.edu.unq.spring.distributed.orchestrator.service;

import ar.edu.unq.unidad3.modelo.Item;
import ar.edu.unq.unidad3.modelo.Personaje;
import ar.edu.unq.unidad3.modelo.Publicacion;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;

@Service
@AllArgsConstructor
public class TransactionCoordinatorService {
    private final PersonajeService personajeService;
    private final StoreService storeService;
    private final ItemService itemService;

    public String onComprarEvent(Long compradorId, Long publicacionId) {
        storeService.pausarPublicacion(publicacionId, compradorId).blockOptional();
        return "Compra en proceso";
    }

    public Result onPublicacionPausada(Long compradorId, Long publicacionId) {
        try {
            var publicacion = storeService.findById(publicacionId).block();
            var item = itemService.cambiarOwner(publicacion.getItemId(), compradorId).block();
            if (item.getOwnerId().equals(compradorId)) {
                return Result.SUCCESS;
            }
            return Result.FAIL;
        } catch(Exception e) {
            return Result.FAIL;
        }
    }

    public Publicacion onPublicarEvent(Long vendedorId, Long itemId, BigDecimal precio) throws JsonProcessingException {
        var item = itemService.findById(itemId).block();
        var vendedor = personajeService.findPersonaje(vendedorId).block();

        var publicacionBody = StoreService.PublicacionBody.builder()
                .personajeId(vendedorId)
                .itemId(itemId)
                .precio(precio)
                .build();

        var publicacion = storeService.publicar(publicacionBody).block();

        var personajeModel = Personaje.builder()
                .id(vendedor.getId())
                .vida(vendedor.getVida())
                .pesoMaximo(vendedor.getPesoMaximo())
                .nombre(vendedor.getNombre())
                .inventario(Collections.emptySet())
                .build();

        var itemModel = Item.builder()
                .nombre(item.getNombre())
                .peso(item.getPeso())
                .id(item.getId())
                .owner(personajeModel)
                .build();

        return Publicacion.builder()
                .id(publicacion.getId())
                .estado(publicacion.getEstado().toModel())
                .precio(publicacion.getPrecio())
                .vendedor(personajeModel)
                .item(itemModel)
                .build();
    }

    public enum Result {
        SUCCESS, FAIL;
    }
}
