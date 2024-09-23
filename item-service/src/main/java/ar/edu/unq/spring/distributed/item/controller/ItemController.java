package ar.edu.unq.spring.distributed.item.controller;

import ar.edu.unq.spring.distributed.item.service.ItemService;
import ar.edu.unq.unidad3.modelo.Item;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/{itemId}")
    public Item findById(@PathVariable("itemId") Long itemId) {
        return itemService.findById(itemId);
    }
}
