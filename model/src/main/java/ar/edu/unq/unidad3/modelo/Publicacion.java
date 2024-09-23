package ar.edu.unq.unidad3.modelo;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Publicacion {
    private Long id;
    private Item item;
    private BigDecimal precio;
    private Personaje vendedor;
    private Estado estado;

    public Publicacion(Item item, Personaje personaje, BigDecimal precio) {
        this.item = item;
        this.vendedor = personaje;
        this.precio = precio;
        this.estado = Estado.ACTIVA;
    }

    public enum Estado {
        ACTIVA, INACTIVA
    }
}
