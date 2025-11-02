package com.uniQuindio.gestionDesastres.model;


import java.util.List;

public class Administrador extends Usuario {

    public Administrador(String nombre, String id, String email, String contrasena) {
        super(nombre, id, email, contrasena);
    }

    public Administrador() {
        super();
    }


    public void manejoRecursos(Recurso recurso, int cantidad) {
        recurso.agregarCantidad(cantidad);
        System.out.println("Recurso actualizado: " + recurso);
    }

    public void asignarRecurso(Recurso recurso, Desastre desastre, int cantidad) {
        if (recurso.consumir(cantidad)) {
            System.out.println("Asignado " + cantidad + " de " + recurso.getNombre() +
                    " al desastre " + desastre.getNombre());
        } else {
            System.out.println("No hay suficientes recursos de " + recurso.getNombre());
        }
    }

    public Ruta definirRuta(Ubicacion origen, Ubicacion destino, float distancia) {
        Ruta ruta = new Ruta(origen, destino, distancia);
        System.out.println("Ruta creada de " + origen.getNombre() + " a " + destino.getNombre() +
                " con distancia " + distancia + " km (peso: " + ruta.calcularPeso() + ")");
        return ruta;
    }

    public void generarReporte(List<Desastre> desastres) {
        System.out.println("Reporte de Desastres Atendidos");
        for (Desastre d : desastres) {
            System.out.println("Desastre: " + d.getNombre() +
                    " | Prioridad: " + d.asignarPrioridad() +
                    " | Equipos: " + d.getEquiposAsignados().size()+
                    " | Recursos: " + d.getPersonasAfectadas());

        }
    }
}
