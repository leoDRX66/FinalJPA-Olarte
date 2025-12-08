package com.mycompany.restaurante.logico;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CLIENTE")
public class Cliente extends Persona {

    public Cliente() {
    }

    public Cliente(String nombre, String cedula, String telefono) {
        super(nombre, cedula, telefono);
    }
}