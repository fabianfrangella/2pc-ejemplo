package ar.edu.unq.spring.distributed.store.service;

import ar.edu.unq.spring.distributed.store.modelo.PublicacionJPADTO;
import ar.edu.unq.spring.distributed.store.repository.PublicacionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;
    private final CoordinatorService coordinatorService;

    public PublicacionJPADTO publicar(Long itemId, Long personajeId, BigDecimal precio) {
        var publicacion = publicacionRepository.save(new PublicacionJPADTO(itemId, personajeId, precio));
        coordinatorService.notificarPublicacion(publicacion.getId()).doOnSuccess((result) -> {
            if (result == CoordinatorService.Result.FAIL) {
                throw new RuntimeException("Rollback publicacion");
            }
        }).block();
        return publicacion;
    }

    public void pausarPublicacion(Long publicacionId, Long compradorId) {
        var publicacion = publicacionRepository.findById(publicacionId).orElseThrow(() -> new RuntimeException("Publicacion not found"));
        publicacion.setEstado(PublicacionJPADTO.Estado.INACTIVA);
        publicacionRepository.save(publicacion);
        coordinatorService.notificarPublicacionPausada(compradorId, publicacionId).doOnSuccess((result) -> {
            if (result == CoordinatorService.Result.FAIL) {
                throw new RuntimeException("Rollback pausa");
            }
        }).block();
    }

    public PublicacionJPADTO findById(Long publicacionId) {
        return publicacionRepository.findById(publicacionId).orElseThrow(() -> new RuntimeException("Publicacion not found"));
    }

    public List<PublicacionJPADTO> findActivas() {
        return publicacionRepository.findByEstado(PublicacionJPADTO.Estado.ACTIVA);
    }
}
