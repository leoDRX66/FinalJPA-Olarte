package com.mycompany.restaurante.logico;

import java.util.ArrayList;


public class Despensa {
    private Gerente gerente;
    private ArrayList<Ingrediente> misIngredientes = new ArrayList<>();

    public Despensa(Gerente gerente) {
        this.gerente = gerente;
    }


    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public ArrayList<Ingrediente> getMisIngredientes() {
        return misIngredientes;
    }

    public void setMisIngredientes(ArrayList<Ingrediente> misIngredientes) {
        this.misIngredientes = misIngredientes;
    }

    public void addIngredientes(Ingrediente i){
        this.misIngredientes.add(i);
    }
 
}
