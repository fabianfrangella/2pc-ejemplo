package ar.edu.unq.spring.distributed.store.service;

import ar.edu.unq.spring.distributed.store.repository.PublicacionRepository;
import ar.edu.unq.unidad3.modelo.Item;
import ar.edu.unq.unidad3.modelo.Personaje;
import ar.edu.unq.unidad3.modelo.Publicacion;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
@Transactional
public class PublicacionService {
    private final PublicacionRepository publicacionRepository;

    public Publicacion publicar(Item item, Personaje personaje, BigDecimal precio) {
        return publicacionRepository.save(new Publicacion(item, personaje, precio));
    }

    public void pausarPublicacion(Long publicacionId) {
        var publicacion = publicacionRepository.findById(publicacionId).orElseThrow(() -> new RuntimeException("Publicacion not found"));
        publicacion.setEstado(Publicacion.Estado.INACTIVA);
    }
}
