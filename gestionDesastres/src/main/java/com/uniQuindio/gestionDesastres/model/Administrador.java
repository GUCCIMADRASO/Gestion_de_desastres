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

    public void asignarRecursos(ColaPrioridad<Desastre> colaPrioridad, List<Recurso> recursosDisponibles) {
        System.out.println("=== ASIGNACIÓN DE RECURSOS SEGÚN PRIORIDAD ===");

        while (!colaPrioridad.estaVacia()) {
            Desastre desastre = colaPrioridad.atenderSiguiente();
            if (desastre == null) break;

            System.out.println("\nAtendiendo desastre: " + desastre.getNombre() +
                    " | Prioridad: " + desastre.asignarPrioridad() +
                    " | Personas afectadas: " + desastre.getPersonasAfectadas());

            for (Recurso recurso : recursosDisponibles) {
                int cantidadNecesaria = 0;

                switch (recurso.getTipo()) {
                    case ALIMENTO:
                        cantidadNecesaria = desastre.getPersonasAfectadas() * 3;
                        break;
                    case MEDICAMENTO:
                        cantidadNecesaria = desastre.getPersonasAfectadas();
                        break;
                    default:
                        cantidadNecesaria = 0;
                        break;
                }

                if (cantidadNecesaria > 0) {
                    if (recurso.getCantidad() >= cantidadNecesaria) {
                        recurso.consumir(cantidadNecesaria);
                        System.out.println(" Asignado " + cantidadNecesaria + " de " + recurso.getNombre());
                    } else if (recurso.getCantidad() > 0) {
                        int disponible = recurso.getCantidad();
                        recurso.consumir(disponible);
                        System.out.println(" Solo se pudieron asignar " + disponible + " de " + recurso.getNombre()
                                + " (faltan " + (cantidadNecesaria - disponible) + ")");
                    } else {
                        System.out.println("No hay disponibilidad de " + recurso.getNombre());
                    }
                }
            }

            System.out.println("→ Asignación completada para el desastre: " + desastre.getNombre());
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
