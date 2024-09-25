package ar.edu.unq.spring.distributed.orchestrator.service;

import ar.edu.unq.unidad3.dto.PersonajeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PersonajeService {

    private final WebClient personajeWebClient;
    private final ObjectMapper objectMapper;

    public Mono<PersonajeDTO> findPersonaje(Long id) {
        return personajeWebClient.get()
                .uri("/personaje/{personajeId}", id)
                .retrieve()
                .bodyToMono(PersonajeDTO.class);
    }

    public Mono<PersonajeDTO> crearPersonaje(PersonajeDTO personaje) throws JsonProcessingException {
        var requestBody = objectMapper.writeValueAsString(personaje);
        return personajeWebClient.post()
                .uri("/personaje")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(PersonajeDTO.class);
    }
}
