package ar.edu.unq.spring.distributed.item.service;

import ar.edu.unq.spring.distributed.item.repository.ItemRepository;
import ar.edu.unq.unidad3.modelo.Item;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
    }
}
