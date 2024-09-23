package ar.edu.unq.spring.distributed.orchestrator.service;

import ar.edu.unq.unidad3.dto.PublicacionDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Map;

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

    public Mono<PublicacionDTO> pausarPublicacion(Long publicacionId, Long compradorId) {
        return storeWebClient.put()
                .uri("/tienda/pausar/{publicacionId}/comprador/{compradorId}", publicacionId, compradorId)
                .retrieve()
                .bodyToMono(PublicacionDTO.class);
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
