package ar.edu.unq.spring.distributed.personaje.controller;

import ar.edu.unq.spring.distributed.personaje.service.PersonajeService;
import ar.edu.unq.unidad3.modelo.Item;
import ar.edu.unq.unidad3.modelo.Personaje;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/personaje")
public class PersonajeController {
    private final PersonajeService personajeService;

    @PostMapping
    public Personaje crearPersonaje(@RequestBody Personaje personaje) {
        return personajeService.crearPersonaje(personaje);
    }

    @GetMapping("/{personajeId}")
    public Personaje findPersonaje(@PathVariable("personajeId") Long personajeId) {
        return personajeService.findById(personajeId);
    }

    @PutMapping("/{personajeId}/item")
    public Personaje agregarItem(@PathVariable("personajeId") Long personajeId, @RequestBody Item item) {
        return personajeService.agregarItem(personajeId, item);
    }

    @PutMapping("/{personajeId}/item/{itemId}")
    public Personaje sacarItem(@PathVariable("personajeId") Long personajeId, @PathVariable Long itemId) {
        return personajeService.sacarItem(personajeId, itemId);
    }
}
