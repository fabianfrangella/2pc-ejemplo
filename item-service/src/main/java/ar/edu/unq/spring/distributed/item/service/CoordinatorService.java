package ar.edu.unq.spring.distributed.item.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CoordinatorService {

    private final WebClient coordinatorWebclient;

    public Mono<Result> publicarCambioDeOwner(Long itemId, Long ownerId) {
        return coordinatorWebclient.post()
                .uri("/notificar/item/{itemId}/owner/{ownerId}", itemId, ownerId)
                .retrieve()
                .bodyToMono(Result.class);
    }

    public enum Result {
        SUCCESS, FAIL;
    }
}
