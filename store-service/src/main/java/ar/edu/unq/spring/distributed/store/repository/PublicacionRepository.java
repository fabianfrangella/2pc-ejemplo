package ar.edu.unq.spring.distributed.store.repository;

import ar.edu.unq.spring.distributed.store.modelo.PublicacionJPADTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<PublicacionJPADTO, Long> {
    List<PublicacionJPADTO> findByEstado(PublicacionJPADTO.Estado estado);
}
