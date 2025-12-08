package com.mycompany.restaurante;

import com.mycompany.restaurante.persistencia.ControladorPrincipal;
import com.mycompany.restaurante.vista.VentanaPrincipal;

public class Restaurante {
    public static void main(String[] args) {
        VentanaPrincipal principal = new VentanaPrincipal();
        new ControladorPrincipal(principal);
    }
}