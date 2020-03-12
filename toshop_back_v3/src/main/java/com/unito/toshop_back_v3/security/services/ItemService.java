package com.unito.toshop_back_v3.security.services;

import com.unito.toshop_back_v3.models.Item;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Validated
public interface ItemService {

    @NotNull Iterable<Item> getAllItems();

    Item getItem(@Min(value = 1L, message = "Invalid product ID.") long id);

    Item save(Item product);

}
