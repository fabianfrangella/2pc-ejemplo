package ar.edu.unq.spring.distributed.store.modelo;

import ar.edu.unq.unidad3.modelo.Publicacion;
import jakarta.persistence.*;
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
@Table(name = "Publicacion")
public class PublicacionJPADTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private BigDecimal precio;
    private Long vendedorId;
    private Estado estado;

    public PublicacionJPADTO(Long itemId, Long personajeId, BigDecimal precio) {
        this.itemId = itemId;
        this.vendedorId = personajeId;
        this.precio = precio;
        this.estado = Estado.ACTIVA;
    }

    public enum Estado {
        ACTIVA, INACTIVA;

        public Publicacion.Estado toModel() {
            return switch (this) {
                case ACTIVA -> Publicacion.Estado.ACTIVA;
                case INACTIVA -> Publicacion.Estado.INACTIVA;
            };
        }
    }


}