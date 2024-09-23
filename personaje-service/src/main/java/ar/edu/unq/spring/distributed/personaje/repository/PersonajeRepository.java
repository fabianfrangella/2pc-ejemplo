package ar.edu.unq.spring.distributed.personaje.repository;


import ar.edu.unq.spring.distributed.personaje.modelo.PersonajeJPADTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends JpaRepository<PersonajeJPADTO, Long> {
}
