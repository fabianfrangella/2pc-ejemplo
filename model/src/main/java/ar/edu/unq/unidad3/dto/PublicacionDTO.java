package ar.edu.unq.unidad3.dto;

import ar.edu.unq.unidad3.modelo.Publicacion;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

import static ar.edu.unq.unidad3.dto.PublicacionDTO.Estado.ACTIVA;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PublicacionDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long itemId;
    private BigDecimal precio;
    private Long vendedorId;
    private Estado estado;

    public PublicacionDTO(Long itemId, Long personajeId, BigDecimal precio) {
        this.itemId = itemId;
        this.vendedorId = personajeId;
        this.precio = precio;
        this.estado = ACTIVA;
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