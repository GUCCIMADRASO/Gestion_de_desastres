package com.uniQuindio.gestionDesastres.model;

import com.uniQuindio.gestionDesastres.model.estructuras.listaSimpleEnlazada.ListaSimpleEnlazada;

public class Departamento {
    private String nombre;
    ListaSimpleEnlazada<Ciudad> ciudades;

    public Departamento(String nombre,ListaSimpleEnlazada<Ciudad> ciudades) {
        this.nombre = nombre;
        this.ciudades = ciudades;
    }

    public Departamento() {}


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ListaSimpleEnlazada<Ciudad> getCiudades() {
        return ciudades;
    }

    public void setCiudades(ListaSimpleEnlazada<Ciudad> ciudades) {
        this.ciudades = ciudades;
    }
}
