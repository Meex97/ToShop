package com.project.toshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "prodotto_fornitore")
public class Prodotto_fornitore extends Prodotto {

    @Column(name = "quantita")
    private Integer nr_pezzi;

    public Prodotto_fornitore() {
    }

    public Prodotto_fornitore(String nome, String descrizione, float prezzo, String categoria, Integer nr_pezzi) {
        super(nome, descrizione, prezzo, categoria);
        this.nr_pezzi= nr_pezzi;
    }



    public Integer getNr_pezzi() {
        return nr_pezzi;
    }

    public void setNr_pezzi(Integer nr_pezzi) {
        this.nr_pezzi = nr_pezzi;
    }
}
