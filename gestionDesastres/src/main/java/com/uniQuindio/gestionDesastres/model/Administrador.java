package com.uniQuindio.gestionDesastres.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Map;

public class Administrador extends Usuario {
    private GrafoDirigido grafoRutas;

    public Administrador(String nombre, String id, String email, String contrasena) {
        super(nombre, id, email, contrasena);
        this.grafoRutas = new GrafoDirigido();

    }

    public Administrador() {
        super();
    }

    public void manejoRecursos(Recurso recurso, int cantidad) {
        recurso.agregarCantidad(cantidad);
        System.out.println("Recurso actualizado: " + recurso);
    }

    public void asignarRecursosConArbol(ArbolDistribucion arbol, List<Recurso> recursosDisponibles) {
        if (arbol == null) {
            System.out.println("Arbol de distribución nulo.");
            return;
        }
        arbol.imprimirEstructura();
        arbol.asignarRecursos(recursosDisponibles);
        System.out.println("Recursos restantes:");
        for (Recurso r : recursosDisponibles) {
            System.out.println(" - " + r.getNombre() + ": " + r.getCantidad());
        }
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
    //metodo para asignarle un equipo al desastre
    public void asignarEquipo(Equipo equipo, Desastre desastre) {
        List<Equipo> equiposAsignados = desastre.getEquiposAsignados();

        // Calcular personal requerido según prioridad
        int personalRequerido = 0;
        String prioridad = desastre.asignarPrioridad();
        switch (prioridad) {
            case "Alta" -> personalRequerido = 20;
            case "Media" -> personalRequerido = 10;
            case "Baja" -> personalRequerido = 5;
        }

        System.out.println("\n INFORMACIÓN DEL DESASTRE:");
        System.out.println("   • Desastre: " + desastre.getNombre());
        System.out.println("   • Ubicación: " + desastre.getUbicacion().getNombre());
        System.out.println("   • Prioridad: " + prioridad);
        System.out.println("   • Personal requerido: " + personalRequerido + " integrantes");

        System.out.println("\n INFORMACIÓN DEL EQUIPO:");
        System.out.println("   • Tipo: " + equipo.getTipoEquipo());
        System.out.println("   • Disponibles: " + equipo.getIntegrantesDisponibles() + " integrantes");

        // Verificar disponibilidad de personal
        if (equipo.getIntegrantesDisponibles() < personalRequerido) {
            System.out.println("\n ASIGNACIÓN RECHAZADA:");
            System.out.println("   No hay suficientes integrantes en el equipo " +
                    equipo.getTipoEquipo() +
                    " para atender el desastre " + desastre.getNombre());
            System.out.println("   Requiere: " + personalRequerido +
                    " | Disponibles: " + equipo.getIntegrantesDisponibles());
            return;
        }

        // ═══════════ CALCULAR RUTA MÁS CORTA ═══════════
        System.out.println("\n CALCULANDO RUTA MÁS CORTA");

        Ubicacion origen = equipo.getUbicacion();
        Ubicacion destino = desastre.getUbicacion();

        // Usar algoritmo de Dijkstra para encontrar la ruta más corta
        List<Ubicacion> rutaMasCorta = Dijkstra.caminoMasCorto(grafoRutas, origen, destino);

        if (rutaMasCorta.isEmpty()) {
            System.out.println("No existe ruta disponible desde " +
                    origen.getNombre() + " hasta " + destino.getNombre());
            System.out.println("No se puede asignar el equipo sin ruta de acceso");
            return;
        }

        // Calcular distancia total
        Map<Ubicacion, Float> distancias = Dijkstra.calcularDistancias(grafoRutas, origen);
        float distanciaTotal = distancias.get(destino);

        // Calcular tiempo estimado (asumiendo velocidad promedio de 40 km/h)
        float tiempoEstimado = (distanciaTotal / 40) * 60; // en minutos

        System.out.println("   ✓ Ruta encontrada:");
        System.out.print("");
        for (int i = 0; i < rutaMasCorta.size(); i++) {
            System.out.print(rutaMasCorta.get(i).getNombre());
            if (i < rutaMasCorta.size() - 1) {
                System.out.print(" → ");
            }
        }
        System.out.println("\n   📏 Distancia total: " + String.format("%.1f", distanciaTotal) + " km");
        System.out.println("   ⏱️  Tiempo estimado de llegada: " +
                String.format("%.0f", tiempoEstimado) + " minutos");

        // Asignar equipo al desastre
        equipo.setIntegrantesDisponibles(equipo.getIntegrantesDisponibles() - personalRequerido);
        equiposAsignados.add(equipo);

        System.out.println("\nASIGNACIÓN EXITOSA:");
        System.out.println("   • Equipo " + equipo.getTipoEquipo() +
                " asignado al desastre " + desastre.getNombre());
        System.out.println("   • Integrantes desplegados: " + personalRequerido);
        System.out.println("   • Integrantes restantes en base: " +
                equipo.getIntegrantesDisponibles());
        System.out.println("   • Estado: En ruta hacia la zona del desastre");
        System.out.println("═══════════════════════════════════════════════════════════════\n");
    }

    public Ruta definirRuta(GrafoDirigido grafo, Ubicacion origen, Ubicacion destino) {

        Map<Ubicacion, Float> distancias = Dijkstra.calcularDistancias(grafo, origen);
        Float distanciaMasCorta = distancias.get(destino);

        if (distanciaMasCorta == null || distanciaMasCorta.isInfinite()) {
            System.out.println("No existe una ruta entre " + origen.getNombre() + " y " + destino.getNombre());
            return null;
        }
        List<Ubicacion> camino = Dijkstra.caminoMasCorto(grafo, origen, destino);

        Ruta ruta = new Ruta(origen, destino, distanciaMasCorta);

        System.out.println("Ruta óptima creada de " + origen.getNombre() + " a " + destino.getNombre());
        System.out.println("Distancia total: " + distanciaMasCorta);
        System.out.println("Peso: " + ruta.calcularPeso());
        System.out.print("Camino más corto: ");

        for (int i = 0; i < camino.size(); i++) {
            System.out.print(camino.get(i).getNombre());
            if (i < camino.size() - 1) System.out.print(" → ");
        }

        System.out.println();

        return ruta;
    }

    public void generarReporte(List<Desastre> desastres) {
        String ruta = "C:\\Users\\Administrator\\Downloads\\reporte.txt";
        File file = new File(ruta);

        try (BufferedWriter writer = Files.newBufferedWriter(
                file.toPath(),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING)) {

            writer.write("Reporte de Desastres Atendidos\n");
            for (Desastre d : desastres) {
                int equipos = d.getEquiposAsignados() != null ? d.getEquiposAsignados().size() : 0;
                writer.write(String.format("Desastre: %s | Prioridad: %s | Equipos: %d | Recursos: %d%n",
                        d.getNombre(),
                        d.asignarPrioridad(),
                        equipos,
                        d.getPersonasAfectadas()));
            }
            System.out.println("Reporte guardado en: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error guardando reporte: " + e.getMessage());
        }
    }
}
