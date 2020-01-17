package com.project.toshop.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "acquisto")
public class Acquisto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "totale")
    private float totale;

    @Column(name = "data")
    private Date data;

    @Column(name = "indirizzo")
    private String indirizzo;

    @Column(name  = "tipo_di_pagamento")
    private String tipo_di_pagamento;

}
