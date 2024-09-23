package ar.edu.unq.spring.distributed.store.controller;

import ar.edu.unq.spring.distributed.store.controller.dto.PublicacionBody;
import ar.edu.unq.spring.distributed.store.service.PublicacionService;
import ar.edu.unq.unidad3.modelo.Publicacion;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tienda")
@AllArgsConstructor
public class TiendaController {
    private final PublicacionService publicacionService;

    @PostMapping("/publicar")
    public Publicacion publicar(@RequestBody PublicacionBody publicacionBody) {
        return publicacionService.publicar(publicacionBody.getItem(), publicacionBody.getPersonaje(), publicacionBody.getPrecio());
    }

    @PutMapping("/pausar/{publicacionId}")
    private void comprar(@PathVariable("publicacionId") Long publicacionId) {
        publicacionService.pausarPublicacion(publicacionId);
    }
}
