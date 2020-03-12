package com.unito.toshop_back_v3.models;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(	name = "item_supplier")
public class Item_Supplier extends Item {

    private int amount;

    public Item_Supplier() {
    }

    public Item_Supplier(String name, String description, float price, Set<Category> categories, int amount) {
        super(name, description, price, categories);
        this.amount=amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
