package com.project.toshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="fornitore")
public class Fornitore extends User {

    @Column(name = "p_iva")
    private String p_IVA;

    @Column(name = "negozio")
    private String negozio;

    public Fornitore() {
    }

    public Fornitore(String name, String surname, String username, String password, String address, String phone, Integer type,String negozio, String p_IVA) {
        super(name, surname, username, password, address, phone,type);
        this.p_IVA = p_IVA;
        this.negozio = negozio;
    }

    public String getP_IVA() {
        return p_IVA;
    }

    public void setP_IVA(String p_IVA) {
        this.p_IVA = p_IVA;
    }

    public String getNegozio() {
        return negozio;
    }

    public void setNegozio(String negozio) {
        this.negozio = negozio;
    }



    public void inserisci_prodotto(){}
}
