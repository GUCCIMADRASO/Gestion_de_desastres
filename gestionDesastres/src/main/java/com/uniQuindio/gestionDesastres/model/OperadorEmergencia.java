package com.uniQuindio.gestionDesastres.model;

import java.util.List;

public class OperadorEmergencia extends Usuario {

    public OperadorEmergencia(String nombre, String id, String email, String contrasena) {
        super(nombre, id, email, contrasena);
    }

    public OperadorEmergencia() {}


    public void monitorearUbicaciones(List<Ubicacion> ubicaciones) {
        System.out.println("Monitoreando ubicaciones:");
        for (Ubicacion u : ubicaciones) {
            System.out.println(u.getNombre() + " (" + u.getCalle() + " y " + u.getCarrera() + ")");
        }
    }

    public void actualizarSituacion(Desastre desastre, int nuevasPersonasAfectadas, int nuevaMagnitud) {
        desastre.setPersonasAfectadas(nuevasPersonasAfectadas);
        desastre.setMagnitud(nuevaMagnitud);
        System.out.println("Situación actualizada del desastre " + desastre.getNombre() +
                " | Magnitud: " + nuevaMagnitud +
                " | Personas afectadas: " + nuevasPersonasAfectadas);
    }
    public void priorizarEvacuacion(Desastre desastre) {
        String prioridad = desastre.asignarPrioridad();
        System.out.println("Evacuación del desastre " + desastre.getNombre() +
                " tiene prioridad: " + prioridad);
    }

    public void coordinarRecursos(Desastre desastre, Equipo equipo) {
        desastre.asignarEquipo(equipo);
    }
}
