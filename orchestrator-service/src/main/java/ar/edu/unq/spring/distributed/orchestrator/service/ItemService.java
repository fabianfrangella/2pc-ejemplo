package ar.edu.unq.spring.distributed.orchestrator.service;

import ar.edu.unq.unidad3.dto.ItemDTO;
import ar.edu.unq.unidad3.dto.PersonajeDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {
    private final WebClient itemWebClient;
    private final ObjectMapper objectMapper;

    public Mono<ItemDTO> findById(Long itemId) {
        return itemWebClient.get()
                .uri("/item/{itemId}", itemId)
                .retrieve()
                .bodyToMono(ItemDTO.class);
    }

    public Mono<ItemDTO> cambiarOwner(Long itemId, Long ownerId) {
        return itemWebClient.put()
                .uri("/item/{itemId}/owner/{ownerId}", itemId, ownerId)
                .retrieve()
                .bodyToMono(ItemDTO.class);
    }

    public Mono<ItemDTO> crearItem(ItemDTO itemDTO) throws JsonProcessingException {
        var requestBody = objectMapper.writeValueAsString(itemDTO);
        return itemWebClient.post()
                .uri("/item")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(ItemDTO.class);
    }

    public Flux<ItemDTO> findByOwnerId(Long personajeId) {
        return itemWebClient.get()
                .uri("/item/owner/{ownerId}", personajeId)
                .retrieve()
                .bodyToFlux(ItemDTO.class);
    }
}
