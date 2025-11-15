package com.uniQuindio.gestionDesastres.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Recursos
        List<Recurso> recursos = Arrays.asList(
                new Recurso("R1", "Agua", TipoRecurso.ALIMENTO, 100),
                new Recurso("R2", "Analgésicos", TipoRecurso.MEDICAMENTO, 50),
                new Recurso("R3", "Arroz", TipoRecurso.ALIMENTO, 200)
        );
        RegistroArchivo.guardarRecursos(recursos);
        System.out.println("Recursos guardados.");

        // Usuario (solo para registrar inicios de sesión)
        Usuario usuario = new Usuario("U1", "Carlos Ruiz", "carlos@example.com", "pass123");
        usuario.iniciarSesion("carlos@example.com", "pass123");
        usuario.iniciarSesion("carlos@example.com", "wrong");
        usuario.cerrarSesion();

        // Ubicaciones
        Ubicacion u1 = new Ubicacion("U1", "Zona Norte", "10", "20", TipoUbicacion.CENTRO_AYUDA);
        Ubicacion u2 = new Ubicacion("U2", "Zona Sur", "5", "15", TipoUbicacion.REFUGIO);
        Ubicacion u3 = new Ubicacion("U3", "Zona Centro", "8", "12", TipoUbicacion.CENTRO_AYUDA);
        RegistroArchivo.guardarUbicaciones(Arrays.asList(u1, u2, u3));
        System.out.println("Ubicaciones guardadas.");

        // Equipos
        Equipo eq1 = new Equipo("E1", 15, TipoEquipo.BOMBEROS);
        Equipo eq2 = new Equipo("E2", 10, TipoEquipo.MEDICOS);
        Equipo eq3 = new Equipo("E3", 8, TipoEquipo.POLICIAS);
        RegistroArchivo.guardarEquipos(Arrays.asList(eq1, eq2, eq3));
        System.out.println("Equipos guardados.");

        // Desastres
        Desastre d1 = new Desastre(4, "Incendio Forestal", "D1", TipoDesastre.INCENDIO, 1200, LocalDate.now(), u1);
        Desastre d2 = new Desastre(2, "Inundación Barrio", "D2", TipoDesastre.INUNDACION, 350, LocalDate.now(), u2);
        RegistroArchivo.guardarDesastres(Arrays.asList(d1, d2));
        System.out.println("Desastres guardados.");

        // Grafo y rutas (requiere que existan GrafoDirigido y Ruta)
        GrafoDirigido grafo = new GrafoDirigido();
        grafo.agregarRuta(new Ruta(u1, u2, 5.5f));
        grafo.agregarRuta(new Ruta(u1, u3, 3.2f));
        grafo.agregarRuta(new Ruta(u2, u3, 4.0f));
        RegistroArchivo.guardarTodasRutasCortas(grafo, u1);
        System.out.println("Rutas cortas guardadas.");

        // Mostrar archivos
        mostrarArchivo("inicios_sesion.txt");
        mostrarArchivo("recursos.txt");
        mostrarArchivo("ubicaciones.txt");
        mostrarArchivo("equipos.txt");
        mostrarArchivo("desastres.txt");
        mostrarArchivo("rutas_cortas.txt");
    }

    private static void mostrarArchivo(String nombreArchivo) {
        System.out.println("\nContenido de " + nombreArchivo + ":");
        try {
            List<String> lineas = Files.readAllLines(Paths.get(nombreArchivo));
            if (lineas.isEmpty()) {
                System.out.println("(Archivo vacío)");
            } else {
                for (String l : lineas) {
                    System.out.println(l);
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo leer " + nombreArchivo + ": " + e.getMessage());
        }
    }
}
