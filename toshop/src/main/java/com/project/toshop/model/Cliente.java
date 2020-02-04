package com.project.toshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends User {

    public Cliente() {
    }

    @Column(name = "premium")
    private Boolean premium;

    @Column(name = "studente")
    private Boolean studente;

    @Column(name = "crediti")
    private Integer crediti;

    public Cliente(String name, String surname, String username, String password, String address, String phone, Integer type,Boolean studente, Boolean premium, Integer crediti) {

        super(name, surname, username, password, address, phone,type);
        this.premium = premium;
        this.studente = studente;
        this.crediti = crediti;
    }



    void acquista(){}

    void baratta_prodotto(){}


    public Integer getCrediti() {
        return crediti;
    }

    public void setCrediti(Integer crediti) {
        this.crediti = crediti;
    }

    public Boolean getPremium() {
        return premium;
    }

    public Boolean getStudente() {
        return studente;
    }

    public void inserisci_prodotto(){

    }

}
