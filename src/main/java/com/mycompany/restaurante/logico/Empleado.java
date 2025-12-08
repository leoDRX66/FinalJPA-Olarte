package com.mycompany.restaurante.logico;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Empleado extends Persona {
    
    @Column(name = "fecha_vinculacion")
    @Temporal(TemporalType.DATE)
    private Date fechaVinculacion;

    @Column(name = "hora_ingreso")
    private Time horaIngreso;

    @Column(name = "hora_salida")
    private Time horaSalida;

    public Empleado() {
    }

    public Empleado(Date fechaVinculacion, Time horaIngreso, Time horaSalida, String nombre, String cedula, String telefono) {
        super(nombre, cedula, telefono);
        this.fechaVinculacion = fechaVinculacion;
        this.horaIngreso = horaIngreso;
        this.horaSalida = horaSalida;
    }

    public Date getFechaVinculacion() { return fechaVinculacion; }
    public void setFechaVinculacion(Date fechaVinculacion) { this.fechaVinculacion = fechaVinculacion; }

    public Time getHoraIngreso() { return horaIngreso; }
    public void setHoraIngreso(Time horaIngreso) { this.horaIngreso = horaIngreso; }

    public Time getHoraSalida() { return horaSalida; }
    public void setHoraSalida(Time horaSalida) { this.horaSalida = horaSalida; }
}