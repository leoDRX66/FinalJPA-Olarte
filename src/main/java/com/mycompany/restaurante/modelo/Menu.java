package com.mycompany.restaurante.modelo;

import com.mycompany.restaurante.modelo.Gerente;
import com.mycompany.restaurante.modelo.Alimento;
import java.util.ArrayList;


public class Menu {
    private Alimento[] alimentos;
    
    //
    private Gerente miGerente;
    private ArrayList<Alimento> mialimentos = new ArrayList<>();

    public Menu(Alimento[] alimentos, Gerente miGerente) {
        this.alimentos = alimentos;
        this.miGerente = miGerente;
    }

    public Alimento[] getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(Alimento[] alimentos) {
        this.alimentos = alimentos;
    }

    public Gerente getMiGerente() {
        return miGerente;
    }

    public void setMiGerente(Gerente miGerente) {
        this.miGerente = miGerente;
    }

    public ArrayList<Alimento> getMialimentos() {
        return mialimentos;
    }

    public void setMialimentos(ArrayList<Alimento> mialimentos) {
        this.mialimentos = mialimentos;
    }   
}
