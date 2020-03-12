package com.unito.toshop_back_v3.controllers;

import com.unito.toshop_back_v3.models.*;
import com.unito.toshop_back_v3.payload.request.SignupRequestClient;
import com.unito.toshop_back_v3.payload.response.MessageResponse;
import com.unito.toshop_back_v3.repository.ItemRepository;
import com.unito.toshop_back_v3.security.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/item")
public class ItemController {



    // productService constructor injection
    private ItemService itemService;

    ItemRepository itemRepository;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //restituisce tutti i prodotti
    @GetMapping(value = { "", "/" })
    public @NotNull Iterable<Item> getItems() {
        return  itemService.getAllItems();
    }

    //Creazione del prodotto
    @PostMapping("/createSupplier")
    public Item_Supplier additemSupplier(@Valid @RequestBody Item_Supplier itemSupplier) {

        Item_Supplier _item_supplier = itemRepository.save(new Item_Supplier(itemSupplier.getName(),itemSupplier.getDescription(), itemSupplier.getPrice(),itemSupplier.getCategories(), itemSupplier.getAmount()));

        return _item_supplier;
    }




}
