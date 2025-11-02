package com.uniQuindio.gestionDesastres.model;

public class Equipo {
    private String idEquipo;
    private int integrantesDisponibles;
    private TipoEquipo tipoEquipo;

    public Equipo(String idEquipo, int integrantesDisponibles, TipoEquipo tipoEquipo) {
        this.idEquipo = idEquipo;
        this.integrantesDisponibles = integrantesDisponibles;
        this.tipoEquipo = tipoEquipo;
    }

    public Equipo(){};

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getIntegrantesDisponibles() {
        return integrantesDisponibles;
    }

    public void setIntegrantesDisponibles(int integrantesDisponibles) {
        this.integrantesDisponibles = integrantesDisponibles;
    }

    public TipoEquipo getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipo tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "idEquipo='" + idEquipo + '\'' +
                ", integrantesDisponibles=" + integrantesDisponibles +
                ", tipoEquipo=" + tipoEquipo +
                '}';
    }
}
