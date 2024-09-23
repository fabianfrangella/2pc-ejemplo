package ar.edu.unq.spring.distributed.orchestrator.service;

import ar.edu.unq.unidad3.dto.PersonajeDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PersonajeService {

    private final WebClient personajeWebClient;

    public Mono<PersonajeDTO> findPersonaje(Long id) {
        return personajeWebClient.get()
                .uri("/personaje/{personajeId}", id)
                .retrieve()
                .bodyToMono(PersonajeDTO.class);
    }

}
