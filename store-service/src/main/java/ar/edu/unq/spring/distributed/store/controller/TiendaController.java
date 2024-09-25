package ar.edu.unq.spring.distributed.store.controller;

import ar.edu.unq.spring.distributed.store.controller.dto.PublicacionBody;
import ar.edu.unq.spring.distributed.store.modelo.PublicacionJPADTO;
import ar.edu.unq.spring.distributed.store.service.PublicacionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tienda")
@AllArgsConstructor
public class TiendaController {
    private final PublicacionService publicacionService;

    @PostMapping("/publicar")
    public PublicacionJPADTO publicar(@RequestBody PublicacionBody publicacionBody) {
        return publicacionService.publicar(publicacionBody.getItemId(), publicacionBody.getPersonajeId(), publicacionBody.getPrecio());
    }

    @PutMapping("/pausar/{publicacionId}/comprador/{compradorId}")
    public void pausar(@PathVariable("publicacionId") Long publicacionId, @PathVariable("compradorId") Long compradorId) {
        publicacionService.pausarPublicacion(publicacionId, compradorId);
    }

    @GetMapping("/publicacion/{publicacionId}")
    public PublicacionJPADTO findById(@PathVariable("publicacionId") Long publicacionId) {
        return publicacionService.findById(publicacionId);
    }

    @GetMapping("/activas")
    public List<PublicacionJPADTO> findActivas() {
        return publicacionService.findActivas();
    }
}
