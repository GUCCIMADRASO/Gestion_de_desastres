package com.uniQuindio.gestionDesastres.model;

public class Ubicacion {
    private String id;
    private String nombre;
    private String calle;
    private String carrera;
    private Ruta entrada;
    private Ruta salida;

    public Ubicacion(String id, String nombre, String calle, String carrera,Ruta entrada,Ruta salida) {
        this.id = id;
        this.nombre = nombre;
        this.calle = calle;
        this.carrera = carrera;
        this.entrada = entrada;
        this.salida = salida;
    }

    public  Ubicacion() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Ruta getEntrada() {
        return entrada;
    }

    public void setEntrada(Ruta entrada) {
        this.entrada = entrada;
    }

    public Ruta getSalida() {
        return salida;
    }

    public void setSalida(Ruta salida) {
        this.salida = salida;
    }
}
