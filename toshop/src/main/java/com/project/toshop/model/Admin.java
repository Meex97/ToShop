package com.project.toshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {

    @Column(name ="matricola")
    private Integer matricola;

    @Column(name = "reparto")
    private String reparto;

    public Admin() {
    }

    public Admin(String name, String surname, String username, String password, String address, String phone, Integer matricola, String reparto) {

        super(name, surname, username, password, address, phone);
        this.matricola = matricola;
        this.reparto = reparto;
    }

    public Integer getMatricola() {
        return matricola;
    }

    public void setMatricola(Integer matricola) {
        this.matricola = matricola;
    }

    public String getReparto() {
        return reparto;
    }

    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public void valuta_prodotto(){}
}
