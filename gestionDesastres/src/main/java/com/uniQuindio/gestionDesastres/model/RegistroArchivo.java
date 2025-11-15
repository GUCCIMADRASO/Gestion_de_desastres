package com.uniQuindio.gestionDesastres.model;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public final class RegistroArchivo {
    private static final String ARCHIVO_TXT_SESIONES = "inicios_sesion.txt";
    private static final String ARCHIVO_TXT_RECURSOS = "recursos.txt";
    private static final String ARCHIVO_TXT_DESASTRES = "desastres.txt";
    private static final String ARCHIVO_TXT_UBICACIONES = "ubicaciones.txt";
    private static final String ARCHIVO_TXT_EQUIPOS = "equipos.txt";
    private static final String ARCHIVO_TXT_RUTAS = "rutas_cortas.txt";

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

    public static void guardarUbicaciones(List<Ubicacion> ubicaciones) {
        try (FileWriter writer = new FileWriter(ARCHIVO_TXT_UBICACIONES, false)) {
            for (Ubicacion u : ubicaciones) {
                String linea = String.format("ID: %s | Nombre: %s | Calle: %s | Carrera: %s | Tipo: %s%n",
                        u.getId(), u.getNombre(), u.getCalle(), u.getCarrera(), u.getTipoUbicacion());
                writer.write(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarEquipos(List<Equipo> equipos) {
        try (FileWriter writer = new FileWriter(ARCHIVO_TXT_EQUIPOS, false)) {
            for (Equipo e : equipos) {
                String linea = String.format("ID: %s | Integrantes: %d | Tipo: %s%n",
                        e.getIdEquipo(), e.getIntegrantesDisponibles(), e.getTipoEquipo());
                writer.write(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarRutaCorta(Ubicacion origen, Ubicacion destino,
                                        List<Ubicacion> ruta, float distanciaTotal) {
        try (FileWriter writer = new FileWriter(ARCHIVO_TXT_RUTAS, true)) {
            String linea = String.format("Origen: %s | Destino: %s | Distancia: %.2f km%n",
                    origen.getNombre(), destino.getNombre(), distanciaTotal);
            writer.write(linea);
            writer.write("Ruta: ");
            for (int i = 0; i < ruta.size(); i++) {
                writer.write(ruta.get(i).getNombre());
                if (i < ruta.size() - 1) writer.write(" -> ");
            }
            writer.write(System.lineSeparator());
            writer.write("---" + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void guardarTodasRutasCortas(GrafoDirigido grafo, Ubicacion origen) {
        try (FileWriter writer = new FileWriter(ARCHIVO_TXT_RUTAS, false)) {
            writer.write("=== Rutas más cortas desde " + origen.getNombre() + " ===" + System.lineSeparator());

            Map<Ubicacion, Float> distancias = grafo.calcularTodasDistancias(origen);
            for (Map.Entry<Ubicacion, Float> entry : distancias.entrySet()) {
                Ubicacion destino = entry.getKey();
                Float dist = entry.getValue();
                if (!destino.equals(origen) && dist != Float.MAX_VALUE) {
                    List<Ubicacion> ruta = grafo.calcularRutaMasCorta(origen, destino);
                    if (ruta != null && !ruta.isEmpty()) {
                        writer.write(String.format("Destino: %s | Distancia: %.2f km%n",
                                destino.getNombre(), dist));
                        writer.write("Ruta: ");
                        for (int i = 0; i < ruta.size(); i++) {
                            writer.write(ruta.get(i).getNombre());
                            if (i < ruta.size() - 1) writer.write(" -> ");
                        }
                        writer.write(System.lineSeparator() + "---" + System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
