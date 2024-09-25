package ar.edu.unq.spring.distributed.orchestrator.service;

import ar.edu.unq.unidad3.dto.PublicacionDTO;
import ar.edu.unq.unidad3.modelo.Publicacion;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreService {
    private final WebClient storeWebClient;

    public Mono<PublicacionDTO> findById(Long publicacionId) {
        return storeWebClient.get()
                .uri("/tienda/publicacion/{publicacionId}", publicacionId)
                .retrieve()
                .bodyToMono(PublicacionDTO.class);
    }

    public Mono<ResponseEntity<Void>> pausarPublicacion(Long publicacionId, Long compradorId) {
        return storeWebClient.put()
                .uri("/tienda/pausar/{publicacionId}/comprador/{compradorId}", publicacionId, compradorId)
                .retrieve()
                .toBodilessEntity();
    }

    public Mono<PublicacionDTO> publicar(PublicacionBody body) throws JsonProcessingException {
        String requestBody = new ObjectMapper().writeValueAsString(body);
        return storeWebClient.post()
                .uri("/tienda/publicar")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(PublicacionDTO.class);
    }

    public Flux<PublicacionDTO> findPublicacionesActivas() {
        return storeWebClient.get()
                .uri("/tienda/activas")
                .retrieve()
                .bodyToFlux(PublicacionDTO.class);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PublicacionBody {
        private Long personajeId;
        private Long itemId;
        private BigDecimal precio;
    }
}
