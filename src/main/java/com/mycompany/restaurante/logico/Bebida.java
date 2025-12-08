package com.mycompany.restaurante.logico;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Bebida")
public class Bebida extends Alimento {

    public Bebida() {
    }

    public Bebida(String nombre, double precio) {
        super(nombre, precio);
    }
}