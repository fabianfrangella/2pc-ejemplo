package ar.edu.unq.unidad3.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Publicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
