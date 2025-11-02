package com.uniQuindio.gestionDesastres.model;

public class Equipo {
    private String idEquipo;
    private int disponible;
    private TipoEquipo tipoEquipo;

    public Equipo(String idEquipo, int disponible, TipoEquipo tipoEquipo) {
        this.idEquipo = idEquipo;
        this.disponible = disponible;
        this.tipoEquipo = tipoEquipo;
    }

    public Equipo(){};

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public TipoEquipo getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipo tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }
}
