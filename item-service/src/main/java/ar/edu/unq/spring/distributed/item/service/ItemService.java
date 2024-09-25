package ar.edu.unq.spring.distributed.item.service;


import ar.edu.unq.spring.distributed.item.modelo.ItemJPADTO;
import ar.edu.unq.spring.distributed.item.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final CoordinatorService coordinatorService;

    public ItemJPADTO findById(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public ItemJPADTO cambiarOwner(Long itemId, Long ownerId) {
        var item = findById(itemId);
        item.setOwnerId(ownerId);
        itemRepository.save(item);
        return item;
    }

    public ItemJPADTO crear(ItemJPADTO item) {
        return itemRepository.save(item);
    }

    public List<ItemJPADTO> findByOwnerId(Long ownerId) {
        return itemRepository.findByOwnerId(ownerId);
    }
}
