// java
package com.uniQuindio.gestionDesastres.model;

import java.time.LocalDate;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        // Admin para pruebas
        Administrador admin = new Administrador("Admin", "A1", "admin@example.com", "admin123");
        admin.iniciarSesion("admin@example.com", "admin123");

        // Recursos globales (lista central)
        List<Recurso> recursosGlobales = new ArrayList<>(Arrays.asList(
                new Recurso("R1", "Agua", TipoRecurso.ALIMENTO, 100),
                new Recurso("R2", "Analgésicos", TipoRecurso.MEDICAMENTO, 50),
                new Recurso("R3", "Arroz", TipoRecurso.ALIMENTO, 200)
        ));
        RegistroArchivo.guardarRecursos(recursosGlobales);
        System.out.println("Recursos globales iniciales registrados.");

        // Ubicaciones
        Ubicacion u1 = new Ubicacion("U1", "Zona Norte", "10", "20", TipoUbicacion.CENTRO_AYUDA);
        Ubicacion u2 = new Ubicacion("U2", "Zona Sur", "5", "15", TipoUbicacion.REFUGIO);
        Ubicacion u3 = new Ubicacion("U3", "Zona Centro", "8", "12", TipoUbicacion.CENTRO_AYUDA);
        RegistroArchivo.guardarUbicaciones(Arrays.asList(u1, u2, u3));

        // Mapa de recursos por ubicación (distribución local)
        MapaRecursos mapa = new MapaRecursos();
        mapa.agregarRecurso(u1, new Recurso("R1", "Agua", TipoRecurso.ALIMENTO, 40));
        mapa.agregarRecurso(u1, new Recurso("R2", "Analgésicos", TipoRecurso.MEDICAMENTO, 15));
        mapa.agregarRecurso(u2, new Recurso("R1", "Agua", TipoRecurso.ALIMENTO, 30));
        mapa.agregarRecurso(u2, new Recurso("R3", "Arroz", TipoRecurso.ALIMENTO, 120));
        mapa.agregarRecurso(u3, new Recurso("R3", "Arroz", TipoRecurso.ALIMENTO, 80));

        // Mostrar inventarios iniciales
        mapa.mostrarInventario(u1);
        mapa.mostrarInventario(u2);
        mapa.mostrarInventario(u3);

        // Consumo local
        System.out.println("\nConsumiendo 10 de Agua en Zona Norte:");
        mapa.consumirRecurso(u1, TipoRecurso.ALIMENTO, 10);
        mapa.mostrarInventario(u1);

        // Transferencia
        System.out.println("\nTransferir 25 de Arroz de Zona Sur a Zona Norte:");
        boolean okTransfer = mapa.transferirRecurso(u2, u1, TipoRecurso.ALIMENTO, 25);
        System.out.println("Transferencia exitosa: " + okTransfer);
        mapa.mostrarInventario(u1);
        mapa.mostrarInventario(u2);

        // Desastres
        Desastre d1 = new Desastre(4, "Incendio Forestal", "D1", TipoDesastre.INCENDIO, 1200, LocalDate.now(), u1);
        Desastre d2 = new Desastre(2, "Inundación Barrio", "D2", TipoDesastre.INUNDACION, 350, LocalDate.now(), u2);
        List<Desastre> desastres = Arrays.asList(d1, d2);
        RegistroArchivo.guardarDesastres(desastres);

        // Cola de prioridad (suponiendo ya implementada)
        ColaPrioridad<Desastre> cola = new ColaPrioridad<>();
        cola.encolar(d1);
        cola.encolar(d2);

        // Asignación de recursos globales por prioridad
        System.out.println("\n--- Asignación de recursos globales ---");
        admin.asignarRecursos(cola, recursosGlobales);

        // Mostrar estado global de recursos post-asignación
        System.out.println("\nEstado global de recursos después de asignación:");
        for (Recurso r : recursosGlobales) {
            System.out.println(" - " + r);
        }

        // Grafo para rutas
        GrafoNoDirigido grafo = new GrafoNoDirigido();
        grafo.agregarRuta(new Ruta(u1, u2, 5.5f));
        grafo.agregarRuta(new Ruta(u2, u3, 4.0f));
        grafo.agregarRuta(new Ruta(u1, u3, 3.2f));
        grafo.agregarRuta(new Ruta(u3, u2, 4.0f));
        admin.cargarGrafoRutas(grafo); // cargar en administrador

        // Equipos con ubicación
        Equipo eq1 = new Equipo("E1", 25, TipoEquipo.BOMBEROS);
        eq1.setUbicacion(u3);
        Equipo eq2 = new Equipo("E2", 12, TipoEquipo.MEDICOS);
        eq2.setUbicacion(u2);
        Equipo eq3 = new Equipo("E3", 10, TipoEquipo.POLICIAS);
        eq3.setUbicacion(u1);
        List<Equipo> equipos = Arrays.asList(eq1, eq2, eq3);
        RegistroArchivo.guardarEquipos(equipos);


        // Asignar equipos a desastres
        System.out.println("\n--- Asignación de equipos ---");
        admin.asignarEquipo(eq1, d1);
        admin.asignarEquipo(eq2, d2);
        admin.asignarEquipo(eq3, d1); // segundo equipo al mismo desastre

        // Verificar integrantes restantes
        System.out.println("\nEstado equipos tras asignación:");
        for (Equipo e : equipos) {
            System.out.println(" - " + e.getIdEquipo() + " (" + e.getTipoEquipo() + ") disponibles: " + e.getIntegrantesDisponibles());
        }

        // Inventarios finales locales
        System.out.println("\nInventarios finales (locales):");
        mapa.mostrarInventario(u1);
        mapa.mostrarInventario(u2);
        mapa.mostrarInventario(u3);

        // Archivos generados
        mostrarArchivo("recursos.txt");
        mostrarArchivo("desastres.txt");
        mostrarArchivo("equipos.txt");
        mostrarArchivo("inicios_sesion.txt");

        // Reporte
        admin.generarReporte(desastres);
        admin.cerrarSesion();
    }

    private static void mostrarArchivo(String nombreArchivo) {
        System.out.println("\nContenido de " + nombreArchivo + ":");
        try {
            List<String> lineas = Files.readAllLines(Paths.get(nombreArchivo));
            if (lineas.isEmpty()) {
                System.out.println("(Archivo vacío)");
            } else {
                for (String l : lineas) System.out.println(l);
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer " + nombreArchivo + ": " + e.getMessage());
        }
    }
    //prueba del algoritmo de dijkstra
    public static void main2(String[] args) {
        Ubicacion zonaNorte = new Ubicacion("Z1", "Zona Norte", "Calle 1", "Carrera 1", TipoUbicacion.CENTRO_AYUDA);
        Ubicacion zonaCentro = new Ubicacion("Z2", "Zona Centro", "Calle 2", "Carrera 2", TipoUbicacion.REFUGIO);
        Ubicacion zonaSur = new Ubicacion("Z3", "Zona Sur", "Calle 3", "Carrera 3", TipoUbicacion.REFUGIO);

        Ruta ruta1 = new Ruta(zonaNorte, zonaCentro, 10f); // 10 km
        Ruta ruta2 = new Ruta(zonaCentro, zonaSur, 15f);   // 15 km
        Ruta ruta3 = new Ruta(zonaNorte, zonaSur, 30f);    // 30 km directo

        GrafoNoDirigido grafo = new GrafoNoDirigido();
        grafo.agregarRuta(ruta1);
        grafo.agregarRuta(ruta2);
        grafo.agregarRuta(ruta3);

        Map<Ubicacion, Float> distancias = Dijkstra.calcularDistancias(grafo, zonaNorte);
        System.out.println("Distancia a Zona Sur: " + distancias.get(zonaSur)); // Esperado: 25.0

        List<Ubicacion> camino = Dijkstra.caminoMasCorto(grafo, zonaNorte, zonaSur);
        System.out.print("Camino más corto: ");
        for (Ubicacion u : camino) {
            System.out.print(u.getNombre() + " → ");
        }
    }
}
