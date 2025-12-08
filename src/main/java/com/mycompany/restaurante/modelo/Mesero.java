package com.mycompany.restaurante.modelo;

import java.sql.Time;
import java.util.Date;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MESERO")
public class Mesero extends Empleado {
    
    private double salario;

    public Mesero() {
    }

    public Mesero(double salario, Date fechaVinculacion, Time horaIngreso, Time horaSalida, String nombre, String cedula, String telefono) {
        super(fechaVinculacion, horaIngreso, horaSalida, nombre, cedula, telefono);
        this.salario = salario;
    }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }
}