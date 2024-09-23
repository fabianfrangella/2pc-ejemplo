package ar.edu.unq.spring.distributed.personaje.service;

import ar.edu.unq.spring.distributed.personaje.modelo.PersonajeJPADTO;
import ar.edu.unq.spring.distributed.personaje.repository.PersonajeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class PersonajeService {

    private final PersonajeRepository personajeRepository;

    public PersonajeJPADTO crearPersonaje(PersonajeJPADTO personaje) {
        return personajeRepository.save(personaje);
    }

    public PersonajeJPADTO findById(Long personajeId) {
        return personajeRepository.findById(personajeId).orElseThrow(() -> new RuntimeException("personaje not found"));
    }

}
