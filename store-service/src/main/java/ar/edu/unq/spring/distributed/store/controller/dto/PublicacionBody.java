package ar.edu.unq.spring.distributed.store.controller.dto;

import ar.edu.unq.unidad3.modelo.Item;
import ar.edu.unq.unidad3.modelo.Personaje;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionBody {
    private Personaje personaje;
    private Item item;
    private BigDecimal precio;
}
