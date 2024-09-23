package ar.edu.unq.spring.distributed.orchestrator.controller;

import ar.edu.unq.spring.distributed.orchestrator.service.TransactionCoordinatorService;
import ar.edu.unq.unidad3.modelo.Publicacion;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

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

    @PutMapping("/comprar/{compradorId}/publicacion/{publicacionId}")
    public String onComprarEvent(@PathVariable("compradorId") Long compradorId,
                                 @PathVariable("publicacionId") Long publicacionId) {
        return transactionalService.onComprarEvent(compradorId, publicacionId);
    }

    @PostMapping("/notificar/comprador/{compradorId}/publicacion/{publicacionId}/pausa")
    public TransactionCoordinatorService.Result onPublicacionPausada(@PathVariable("compradorId") Long compradorId, @PathVariable("publicacionId") Long publicacionId) {
        return transactionalService.onPublicacionPausada(compradorId, publicacionId);

    }

    @PostMapping("/notificar/publicacion/{publicacionId}")
    public TransactionCoordinatorService.Result onPublicacionCreada(@PathVariable("publicacionId") Long publicacionId) {
        return TransactionCoordinatorService.Result.SUCCESS;
    }
}
