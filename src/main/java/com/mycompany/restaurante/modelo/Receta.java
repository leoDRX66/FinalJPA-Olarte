package com.mycompany.restaurante.modelo;

import com.mycompany.restaurante.modelo.Ingrediente;
import com.mycompany.restaurante.modelo.Chef;
import java.util.ArrayList;

public class Receta {
    private Chef chef;
    private String nombreReceta;
    private String descripcionProceso;
    private ArrayList<Ingrediente> ingredientes = new ArrayList<>();

    public Receta(Chef chef, String nombreReceta, String descripcionProceso) {
        this.chef = chef;
        this.nombreReceta = nombreReceta;
        this.descripcionProceso = descripcionProceso;
    }
    
    public void addIngredientes(Ingrediente i){
        this.ingredientes.add(i);
    }

    public Chef getChef() { 
        return chef; 
    }
    
    public String getNombreReceta() { 
        return nombreReceta; 
    }
    
    public ArrayList<Ingrediente> getIngredientes() { 
        return ingredientes; 
    }
    
}