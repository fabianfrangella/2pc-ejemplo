package ar.edu.unq.spring.distributed.item.controller;


import ar.edu.unq.spring.distributed.item.modelo.ItemJPADTO;
import ar.edu.unq.spring.distributed.item.service.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{itemId}")
    public ItemJPADTO findById(@PathVariable("itemId") Long itemId) {
        return itemService.findById(itemId);
    }

    @PutMapping("/{itemId}/owner/{ownerId}")
    public ItemJPADTO cambiarOwner(@PathVariable("itemId") Long itemId, @PathVariable("ownerId") Long ownerId) {
        return itemService.cambiarOwner(itemId, ownerId);
    }

    @PostMapping
    public ItemJPADTO crearItem(@RequestBody ItemJPADTO item) {
        return itemService.crear(item);
    }

    @GetMapping("/owner/{ownerId}")
    public List<ItemJPADTO> findByOwnerId(@PathVariable("ownerId") Long ownerId) {
        return itemService.findByOwnerId(ownerId);
    }
}
