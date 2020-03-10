package com.unito.toshop_back_v3.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(	name = "supplier")
public class Supplier extends User{

    private String pIVA;

    private String address;

    private String shopName;

    public Supplier() {
    }

    public Supplier(String username, String email, String password, String name, String surname, String phone, String pIVA, String address, String shopName) {
        super(username, email, password, name, surname, phone);
        this.pIVA= pIVA;
        this.address= address;
        this.shopName = shopName;
    }

    public String getpIVA() {
        return pIVA;
    }

    public void setpIVA(String pIVA) {
        this.pIVA = pIVA;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
