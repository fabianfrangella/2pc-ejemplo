package ar.edu.unq.spring.distributed.orchestrator.service;

import ar.edu.unq.unidad3.dto.ItemDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class ItemService {
    private final WebClient itemWebClient;

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
}
