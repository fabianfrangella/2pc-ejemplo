package ar.edu.unq.unidad3.dto;

import ar.edu.unq.unidad3.modelo.Item;
import ar.edu.unq.unidad3.modelo.Personaje;
import lombok.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class PersonajeDTO implements Serializable {


    private Long id;

    private String nombre;

    private Integer vida;
    private Integer pesoMaximo;


    public PersonajeDTO(@NonNull String nombre, @NonNull Integer vida, @NonNull Integer pesoMaximo) {
        this.nombre = nombre;
        this.vida = vida;
        this.pesoMaximo = pesoMaximo;
    }

    public Personaje toModel() {
        return Personaje.builder()
                .id(getId())
                .vida(getVida())
                .pesoMaximo(getPesoMaximo())
                .nombre(getNombre())
                .inventario(Collections.emptySet())
                .build();
    }

    public Personaje toModel(Set<Item> inventario) {
        return Personaje.builder()
                .id(getId())
                .vida(getVida())
                .pesoMaximo(getPesoMaximo())
                .nombre(getNombre())
                .inventario(inventario)
                .build();
    }

}

