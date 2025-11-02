package com.uniQuindio.gestionDesastres.model;

public class Administrador extends Usuario {

    public Administrador(String nombre, String id, String email, String contraseña) {
        super(nombre, id, email, contraseña);
    }
    public Administrador() {
        super();
    }

    public void manejoRecursos() {
        System.out.println("Gestionando recursos disponibles en el sistema...");
    }

    public void asignarRecursos(String zona, String recurso, int cantidad) {
        System.out.println("Asignando " + cantidad + " unidades de " + recurso + " a la zona " + zona);
    }

    public void definirRuta(String origen, String destino) {
        System.out.println("Definiendo nueva ruta entre " + origen + " y " + destino);
    }

    public void generarReporte() {
        System.out.println("Generando reporte de recursos y rutas...");
    }
}
