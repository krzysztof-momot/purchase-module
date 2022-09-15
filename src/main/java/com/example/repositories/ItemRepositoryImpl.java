package com.example.repositories;

import com.example.repositories.jpa.ItemJPARepository;
import com.example.domain.Item;
import com.example.domain.ItemRepository;
import org.springframework.stereotype.Component;

@Component
public class ItemRepositoryImpl implements ItemRepository {

    private final ItemJPARepository itemJPARepository;

    public ItemRepositoryImpl(ItemJPARepository itemJPARepository) {
        this.itemJPARepository = itemJPARepository;
    }

    @Override
    public Item get(String id) {
        return itemJPARepository.getReferenceById(id).toItem();
    }
}
