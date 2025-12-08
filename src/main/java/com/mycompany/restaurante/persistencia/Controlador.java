package com.mycompany.restaurante.persistencia;

import com.mycompany.restaurante.logico.Pedido;
import com.mycompany.restaurante.logico.Chef;
import com.mycompany.restaurante.logico.Persona;
import com.mycompany.restaurante.logico.Mesero;
import com.mycompany.restaurante.logico.Alimento;
import com.mycompany.restaurante.persistencia.ControladorPersistencia;
import java.util.ArrayList;
import java.util.List;

public class Controlador {
    
    ControladorPersistencia controlPersis = new ControladorPersistencia();

    public void guardarAlimento(Alimento alimento) {
        controlPersis.crear(alimento);
    }

    public void eliminarAlimento(int id) {
        controlPersis.eliminar(Alimento.class, id);
    }

    public List<Alimento> traerAlimentos() {
        return controlPersis.traerAlimentos();
    }

    public List<Persona> traerPersonal(String tipo) {
        if (tipo.equalsIgnoreCase("CHEF")) {
            return (List<Persona>)(List<?>) controlPersis.traerPersonasPorTipo(Chef.class);
        } else if (tipo.equalsIgnoreCase("MESERO")) {
            return (List<Persona>)(List<?>) controlPersis.traerPersonasPorTipo(Mesero.class);
        }
        return new ArrayList<>();
    }

    public void guardarPedido(Pedido pedido) {
        controlPersis.crear(pedido);
    }

    public List<Pedido> traerPedidos() {
        return controlPersis.traerPedidos();
    }

    public void eliminarPedido(int id) {
        controlPersis.eliminar(Pedido.class, id);
    }
}