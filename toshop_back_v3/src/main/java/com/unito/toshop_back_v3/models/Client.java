package com.unito.toshop_back_v3.models;


import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(	name = "client")
public class Client extends User {

    private String address;

    private int credits;

    public Client() {
    }

    public Client(String username, String email, String password, String name, String surname, String phone, String address) {
        super(username, email, password, name, surname, phone);
        this.address = address;
        this.credits = 0;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
