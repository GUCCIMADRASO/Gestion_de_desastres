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

    public void coordinarRecursos(Desastre desastre, Equipo equipo) {


    }
    public void gestionarEvacuaciones(List<Desastre> desastres) {
        ColaPrioridad<Desastre> colaEvacuacion = new ColaPrioridad<>();
        for (Desastre d : desastres) {
            colaEvacuacion.agregarElemento(d);
        }

        while (!colaEvacuacion.estaVacia()) {
            Desastre siguiente = colaEvacuacion.atenderSiguiente();
            if (siguiente != null) {
                System.out.println("➡️ Evacuando zona afectada: " + siguiente.getNombre() +
                        " | Prioridad: " + siguiente.asignarPrioridad() +
                        " | Personas afectadas: " + siguiente.getPersonasAfectadas());
            }
        }

        System.out.println("Todas las evacuaciones fueron gestionadas.");
    }

}
