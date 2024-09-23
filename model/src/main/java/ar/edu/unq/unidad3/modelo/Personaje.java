package ar.edu.unq.unidad3.modelo;

import ar.edu.unq.unidad3.modelo.exception.MuchoPesoException;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Personaje implements Serializable {

    private Long id;
    private String nombre;

    private Integer vida;
    private Integer pesoMaximo;

    private Set<Item> inventario = new HashSet<>();

    public Personaje(@NonNull String nombre, @NonNull Integer vida,  @NonNull Integer pesoMaximo) {
        this.nombre = nombre;
        this.vida = vida;
        this.pesoMaximo = pesoMaximo;
    }

    public int getPesoActual() {
        return inventario.stream().mapToInt(Item::getPeso).sum();
    }

    public void recoger(Item item) {
        int pesoActual = this.getPesoActual();
        if (pesoActual + item.getPeso() > this.pesoMaximo) {
            throw new MuchoPesoException(this, item);
        }
        this.inventario.add(item);
        item.setOwner(this);
    }

    public void eliminarItem(Long itemId) {
        this.inventario = inventario.stream()
                .filter((item) -> item.getId().equals(itemId))
                .collect(Collectors.toSet());
    }
}