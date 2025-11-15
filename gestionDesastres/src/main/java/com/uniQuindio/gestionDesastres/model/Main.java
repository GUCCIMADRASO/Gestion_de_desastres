package com.uniQuindio.gestionDesastres.model;
// java

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Recursos
        List<Recurso> recursos = Arrays.asList(
                new Recurso("R1", "Agua", TipoRecurso.ALIMENTO, 100),
                new Recurso("R2", "Analgésicos", TipoRecurso.MEDICAMENTO, 50),
                new Recurso("R3", "Arroz", TipoRecurso.ALIMENTO, 200)
        );
        RegistroArchivo.guardarRecursos(recursos);
        System.out.println("Recursos guardados en recursos.txt.");

        // Usuario
        Usuario usuario = new Usuario("U1", "Carlos Ruiz", "carlos@example.com", "pass123");
        usuario.iniciarSesion("carlos@example.com", "pass123");
        usuario.iniciarSesion("carlos@example.com", "wrong");
        usuario.cerrarSesion();

        // Desastres
        Ubicacion u1 = new Ubicacion("Zona Norte", "10", "20","19",TipoUbicacion.CENTRO_AYUDA);
        Desastre d1 = new Desastre(4, "Incendio Forestal", "D1", TipoDesastre.INCENDIO, 1200, LocalDate.now(), u1);
        Desastre d2 = new Desastre(2, "Inundación Barrio", "D2", TipoDesastre.INUNDACION, 350, LocalDate.now(), u1);
        RegistroArchivo.guardarDesastres(Arrays.asList(d1, d2));
        System.out.println("Desastres guardados en desastres.txt.");

        // Mostrar archivos
        mostrarArchivo("recursos.txt");
        mostrarArchivo("inicios_sesion.txt");
        mostrarArchivo("desastres.txt");
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
