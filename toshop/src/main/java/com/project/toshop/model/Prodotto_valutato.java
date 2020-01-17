package com.project.toshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "prodotto_valutato")
public class Prodotto_valutato extends Prodotto {

    @Column(name = "codice")
    private Integer codice;

    @Column(name = "prezzo_proposto")
    private float prezzo_proposto;

    @Column(name = "valutabile")
    private boolean valutabile;

    @Column(name = "valutato")
    private boolean valutato;

    public Prodotto_valutato() {
    }

    public Prodotto_valutato(String nome, String descrizione, float prezzo, String categoria, Integer codice, float prezzo_proposto, boolean valutabile, boolean valutato) {
        super(nome, descrizione, prezzo, categoria);
        this.codice = codice;
        this.prezzo_proposto = prezzo_proposto;
        this.valutabile = valutabile;
        this.valutato = valutato;
    }

    public Integer getCodice() {
        return codice;
    }

    public void setCodice(Integer codice) {
        this.codice = codice;
    }

    public float getPrezzo_proposto() {
        return prezzo_proposto;
    }

    public void setPrezzo_proposto(float prezzo_proposto) {
        this.prezzo_proposto = prezzo_proposto;
    }

    public boolean isValutabile() {
        return valutabile;
    }

    public void setValutabile(boolean valutabile) {
        this.valutabile = valutabile;
    }

    public boolean isValutato() {
        return valutato;
    }

    public void setValutato(boolean valutato) {
        this.valutato = valutato;
    }


   // public void vendibile (){}
}
