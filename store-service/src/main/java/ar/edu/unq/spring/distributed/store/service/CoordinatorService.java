package ar.edu.unq.spring.distributed.store.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
// TODO: CoordinatorClient o Controller
public class CoordinatorService {

    private final WebClient coordinatorWebclient;

    public Mono<Result> notificarPublicacionPausada(Long compradorId, Long publicacionId) {
        return coordinatorWebclient.post()
                .uri("/notificar/comprador/{compradorId}/publicacion/{publicacionId}/pausa", compradorId, publicacionId)
                .retrieve()
                .bodyToMono(Result.class);
    }

    public Mono<Result> notificarPublicacion(Long publicacionId) {
        return coordinatorWebclient.post()
                .uri("/notificar/publicacion/{publicacionId}", publicacionId)
                .retrieve()
                .bodyToMono(Result.class);
    }

    public enum Result {
        SUCCESS, FAIL;
    }
}
