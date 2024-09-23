package ar.edu.unq.spring.distributed.personaje.repository;

import ar.edu.unq.unidad3.modelo.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
