package com.uniQuindio.gestionDesastres.model;


import com.uniQuindio.gestionDesastres.model.Recurso;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public final class RegistroArchivo {
    private static final String ARCHIVO_TXT_SESIONES = "inicios_sesion.txt";
    private static final String ARCHIVO_TXT_RECURSOS = "recursos.txt";
    private static final String ARCHIVO_TXT_DESASTRES = "desastres.txt";

    private RegistroArchivo() {}

    public static void registrarInicioSesion(String nombre, String email) {
        String registro = String.format("Nombre: %s | Email: %s | FechaHora: %s%n",
                nombre, email, LocalDateTime.now());
        try (FileWriter writer = new FileWriter(ARCHIVO_TXT_SESIONES, true)) {
            writer.write(registro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarRecursos(List<Recurso> recursos) {
        try (FileWriter writer = new FileWriter(ARCHIVO_TXT_RECURSOS, false)) {
            for (Recurso recurso : recursos) {
                String registro = String.format("ID: %s | Nombre: %s | Tipo: %s | Cantidad: %d%n",
                        recurso.getId(), recurso.getNombre(), recurso.getTipo(), recurso.getCantidad());
                writer.write(registro);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void guardarDesastres(List<Desastre> desastres) {
        try (FileWriter writer = new FileWriter(ARCHIVO_TXT_DESASTRES, false)) {
            for (Desastre d : desastres) {
                writer.write(d.toLineaArchivo() + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
