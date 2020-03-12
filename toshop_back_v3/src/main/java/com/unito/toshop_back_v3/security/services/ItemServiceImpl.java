package com.unito.toshop_back_v3.security.services;

import com.unito.toshop_back_v3.exception.ResourceNotFoundException;
import com.unito.toshop_back_v3.models.Item;
import com.unito.toshop_back_v3.repository.ItemRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ItemServiceImpl implements ItemService{

    // productRepository constructor injection

    ItemRepository itemRepository;

    @Override
    public Iterable<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItem(long id) {
        return itemRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }
}
