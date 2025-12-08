package com.mycompany.restaurante.logico;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Postre")
public class Postres extends Alimento {

    public Postres() {
    }

    public Postres(String nombre, double precio) {
        super(nombre, precio);
    }
}