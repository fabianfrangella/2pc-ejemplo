package ar.edu.unq.spring.distributed.personaje.service;

import ar.edu.unq.spring.distributed.personaje.repository.PersonajeRepository;
import ar.edu.unq.unidad3.modelo.Item;
import ar.edu.unq.unidad3.modelo.Personaje;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class PersonajeService {

    private final PersonajeRepository personajeRepository;

    public final Personaje crearPersonaje(Personaje personaje) {
        var itemInicial = new Item("Daga", 10);
        personaje.recoger(itemInicial);
        return personajeRepository.save(personaje);
    }

    public Personaje findById(Long personajeId) {
        return personajeRepository.findById(personajeId).orElseThrow(() -> new RuntimeException("personaje not found"));
    }

    public Personaje agregarItem(Long personajeId, Item item) {
        var personaje = findById(personajeId);
        personaje.recoger(item);
        personajeRepository.save(personaje);
        return personaje;
    }

    public Personaje sacarItem(Long personajeId, Long itemId) {
        var personaje = findById(personajeId);
        personaje.eliminarItem(itemId);
        personajeRepository.save(personaje);
        return personaje;
    }
}
