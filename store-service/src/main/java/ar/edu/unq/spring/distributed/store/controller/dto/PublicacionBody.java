package ar.edu.unq.spring.distributed.store.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublicacionBody {
    private Long personajeId;
    private Long itemId;
    private BigDecimal precio;
}
