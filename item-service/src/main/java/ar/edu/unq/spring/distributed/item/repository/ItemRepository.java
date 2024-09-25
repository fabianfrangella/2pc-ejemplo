package ar.edu.unq.spring.distributed.item.repository;

import ar.edu.unq.spring.distributed.item.modelo.ItemJPADTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemJPADTO, Long> {
    List<ItemJPADTO> findByOwnerId(Long ownerId);
}
