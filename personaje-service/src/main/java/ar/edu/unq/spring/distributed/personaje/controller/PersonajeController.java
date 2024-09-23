package ar.edu.unq.spring.distributed.personaje.controller;

import ar.edu.unq.spring.distributed.personaje.modelo.PersonajeJPADTO;
import ar.edu.unq.spring.distributed.personaje.service.PersonajeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/personaje")
public class PersonajeController {

    private final PersonajeService personajeService;

    @PostMapping
    public PersonajeJPADTO crearPersonaje(@RequestBody PersonajeJPADTO personaje) {
        return personajeService.crearPersonaje(personaje);
    }

    @GetMapping("/{personajeId}")
    public PersonajeJPADTO findPersonaje(@PathVariable("personajeId") Long personajeId) {
        return personajeService.findById(personajeId);
    }

}
