package ar.edu.unq.spring.distributed.orchestrator.controller;

import ar.edu.unq.unidad3.modelo.Publicacion;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class TransactionalController {

    public Publicacion publicar() {
        // obtener personaje
        // obtener item
        // guardar publicacion
        return null;
    }

    public Publicacion comprar() {
        // obtener personaje comprador
        // obtener personaje vendedor
        // obtener publicacion
        // mover item de vendedor a comprador
        // setear publicacion como inactiva
        return null;
    }
}
