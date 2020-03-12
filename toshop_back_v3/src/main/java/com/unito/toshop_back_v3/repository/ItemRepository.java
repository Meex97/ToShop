package com.unito.toshop_back_v3.repository;

import com.unito.toshop_back_v3.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Override
    void delete(Item deleted);
}
