package com.mycompany.restaurante.logico;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Adicional")
public class Adicionales extends Alimento {

    public Adicionales() {
    }

    public Adicionales(String nombre, double precio) {
        super(nombre, precio);
    }
}