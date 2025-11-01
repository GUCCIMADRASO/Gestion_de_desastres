package com.uniQuindio.gestionDesastres.model;

import com.uniQuindio.gestionDesastres.model.estructuras.listaSimpleEnlazada.ListaSimpleEnlazada;

public class Ciudad {
    private String nombre;
    private Departamento departamento;
    ListaSimpleEnlazada<Barrio> barrios;

    public Ciudad(String nombre,Departamento departamento,ListaSimpleEnlazada<Barrio> barrios) {
        this.nombre = nombre;
        this.departamento = departamento;
        this.barrios = barrios;
    }

    public Ciudad() {}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public ListaSimpleEnlazada<Barrio> getBarrios() {
        return barrios;
    }

    public void setBarrios(ListaSimpleEnlazada<Barrio> barrios) {
        this.barrios = barrios;
    }
}
