package com.mycompany.restaurante.modelo;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("GERENTE")
public class Gerente extends Persona {

    public Gerente() {
    }

    public Gerente(String nombre, String cedula, String telefono) {
        super(nombre, cedula, telefono);
    }
}