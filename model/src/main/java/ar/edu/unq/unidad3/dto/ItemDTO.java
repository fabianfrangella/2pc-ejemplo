package ar.edu.unq.unidad3.dto;

import ar.edu.unq.unidad3.modelo.Item;
import ar.edu.unq.unidad3.modelo.Personaje;
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

    public Item toModel() {
        return Item.builder()
                .nombre(getNombre())
                .peso(getPeso())
                .id(getId())
                .build();
    }

    public Item toModel(Personaje owner) {
        return Item.builder()
                .nombre(getNombre())
                .peso(getPeso())
                .id(getId())
                .owner(owner)
                .build();
    }
}