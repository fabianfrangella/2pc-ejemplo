package ar.edu.unq.spring.distributed.orchestrator.controller;

import ar.edu.unq.spring.distributed.orchestrator.service.TransactionCoordinatorService;
import ar.edu.unq.unidad3.dto.ItemDTO;
import ar.edu.unq.unidad3.dto.PersonajeDTO;
import ar.edu.unq.unidad3.dto.PublicacionDTO;
import ar.edu.unq.unidad3.modelo.Personaje;
import ar.edu.unq.unidad3.modelo.Publicacion;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class TransactionCoordinatorController {

    private final TransactionCoordinatorService transactionalService;

    @PostMapping("/publicar/vendedor/{vendedorId}/item/{itemId}/precio/{precio}")
    public Publicacion onPublicarEvent(@PathVariable("vendedorId") Long vendedorId,
                                       @PathVariable("itemId") Long itemId,
                                       @PathVariable("precio") BigDecimal precio) throws JsonProcessingException {
        return transactionalService.onPublicarEvent(vendedorId, itemId, precio);
    }

    @PostMapping("/notificar/comprador/{compradorId}/publicacion/{publicacionId}/pausa")
    public TransactionCoordinatorService.Result onPublicacionPausada(@PathVariable("compradorId") Long compradorId,
                                                                     @PathVariable("publicacionId") Long publicacionId) {
        return transactionalService.onPublicacionPausada(compradorId, publicacionId);
    }

    @PutMapping("/comprar/{compradorId}/publicacion/{publicacionId}")
    public Long onComprarEvent(@PathVariable("compradorId") Long compradorId,
                               @PathVariable("publicacionId") Long publicacionId) {
        return transactionalService.onComprarEvent(compradorId, publicacionId);
    }


    @PostMapping("/notificar/publicacion/{publicacionId}")
    public TransactionCoordinatorService.Result onPublicacionCreada(@PathVariable("publicacionId") Long publicacionId) {
        return TransactionCoordinatorService.Result.SUCCESS;
    }

    @PostMapping("/personaje")
    public PersonajeDTO crearPersonaje(@RequestBody PersonajeDTO personaje) throws JsonProcessingException {
        return transactionalService.crearPersonaje(personaje);
    }

    @GetMapping("/personaje/{personajeId}")
    public Personaje getPersonaje(@PathVariable Long personajeId) {
        return transactionalService.getPersonaje(personajeId);
    }

    @PostMapping("/item")
    public ItemDTO crearItem(@RequestBody ItemDTO item) throws JsonProcessingException {
        return transactionalService.crearItem(item);
    }

    @GetMapping("/publicacion/{publicacionId}")
    public Publicacion findPublicacion(@PathVariable("publicacionId") Long publicacionId) {
        return transactionalService.findPublicacionById(publicacionId);
    }

    @GetMapping("/publicacion/activas")
    public List<Publicacion> findPublicacionesActivas() {
        return transactionalService.findPublicacionesActivas();
    }

}
