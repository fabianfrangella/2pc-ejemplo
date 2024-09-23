package ar.edu.unq.unidad3.dto;

import lombok.*;

import java.io.Serializable;


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

}

