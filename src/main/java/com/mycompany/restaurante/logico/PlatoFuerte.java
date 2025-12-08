package com.mycompany.restaurante.logico;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PlatoFuerte")
public class PlatoFuerte extends Alimento {

    public PlatoFuerte() {
    }

    public PlatoFuerte(String nombre, double precio) {
        super(nombre, precio);
    }
}