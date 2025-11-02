package com.uniQuindio.gestionDesastres.model;

import java.time.LocalDate;

public class Desastre {

    private int magnitud;
    private String nombre;
    private String idDesastre;
    private TipoDesastre  tipoDesastre;
    private int personasAfectadas;
    private LocalDate fecha;
    private Ubicacion ubicacion;

    public Desastre(int magnitud, String nombre, String idDesastre, TipoDesastre tipoDesastre,
                    int personasAfectadas, LocalDate fecha,Ubicacion ubicacion) {
        this.magnitud = magnitud;
        this.nombre = nombre;
        this.idDesastre = idDesastre;
        this.tipoDesastre = tipoDesastre;
        this.personasAfectadas = personasAfectadas;
        this.fecha = fecha;
        this.ubicacion = ubicacion;
    }

    public Desastre(){};

    public String asignarPrioridad(){
        if (magnitud >= 4 || personasAfectadas > 1000) {
            return "Alta";
        } else if (magnitud == 3 || (personasAfectadas >= 300 && personasAfectadas <= 1000)) {
            return "Media";
        } else {
            return "Baja";
        }
    }

    public int getMagnitud() {
        return magnitud;
    }

    public void setMagnitud(int magnitud) {
        this.magnitud = magnitud;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdDesastre() {
        return idDesastre;
    }

    public void setIdDesastre(String idDesastre) {
        this.idDesastre = idDesastre;
    }

    public TipoDesastre getTipoDesastre() {
        return tipoDesastre;
    }

    public void setTipoDesastre(TipoDesastre tipoDesastre) {
        this.tipoDesastre = tipoDesastre;
    }

    public int getPersonasAfectadas() {
        return personasAfectadas;
    }

    public void setPersonasAfectadas(int personasAfectadas) {
        this.personasAfectadas = personasAfectadas;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
