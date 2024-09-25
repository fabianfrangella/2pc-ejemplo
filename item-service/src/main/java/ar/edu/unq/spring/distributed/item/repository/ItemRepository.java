package ar.edu.unq.spring.distributed.item.repository;

import ar.edu.unq.spring.distributed.item.modelo.ItemJPADTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemJPADTO, Long> {
}
