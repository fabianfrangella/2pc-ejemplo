package ar.edu.unq.unidad3.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
public final class ItemDTO implements Serializable {

    private Long id;

    private String nombre;
    private Integer peso;

    private Long ownerId;

    public ItemDTO(@NonNull String nombre, @NonNull Integer peso) {
        this.nombre = nombre;
        this.peso = peso;
    }
}