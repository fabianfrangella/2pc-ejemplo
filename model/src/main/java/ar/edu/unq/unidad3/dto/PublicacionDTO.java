package ar.edu.unq.unidad3.dto;

import ar.edu.unq.unidad3.modelo.Item;
import ar.edu.unq.unidad3.modelo.Personaje;
import ar.edu.unq.unidad3.modelo.Publicacion;
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

    public Publicacion toModel(Personaje personaje, Item item) {
        return Publicacion.builder()
                .id(getId())
                .estado(getEstado().toModel())
                .precio(getPrecio())
                .vendedor(personaje)
                .item(item)
                .build();
    }


}