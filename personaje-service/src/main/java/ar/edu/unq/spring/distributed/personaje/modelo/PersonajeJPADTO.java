package ar.edu.unq.spring.distributed.personaje.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Builder
@AllArgsConstructor
@Table(name = "Personaje")
public class PersonajeJPADTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 500)
    private String nombre;

    private Integer vida;
    private Integer pesoMaximo;


    public PersonajeJPADTO(@NonNull String nombre, @NonNull Integer vida, @NonNull Integer pesoMaximo) {
        this.nombre = nombre;
        this.vida = vida;
        this.pesoMaximo = pesoMaximo;
    }

}

