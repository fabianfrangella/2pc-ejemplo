package ar.edu.unq.spring.distributed.item.modelo;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Item")
public final class ItemJPADTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer peso;

    private Long ownerId;

    public ItemJPADTO(@NonNull String nombre, @NonNull Integer peso) {
        this.nombre = nombre;
        this.peso = peso;
    }
}