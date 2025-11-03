package com.uniQuindio.gestionDesastres.model;

import java.util.*;

public class GrafoDirigido {
    private Map<Ubicacion, List<Ruta>> adyacencias = new HashMap<>();

    public void agregarUbicacion(Ubicacion ubicacion) {
        adyacencias.putIfAbsent(ubicacion, new ArrayList<>());
    }

    public void agregarRuta(Ruta ruta) {
        agregarUbicacion(ruta.getOrigen());
        agregarUbicacion(ruta.getDestino());
        adyacencias.get(ruta.getOrigen()).add(ruta);
    }

    public List<Ruta> obtenerRutasDesde(Ubicacion origen) {
        return adyacencias.getOrDefault(origen, new ArrayList<>());
    }

    public Set<Ubicacion> obtenerUbicaciones() {
        return adyacencias.keySet();
    }

    public void imprimirGrafo() {
        for (Ubicacion origen : adyacencias.keySet()) {
            System.out.print(origen + " -> ");
            List<Ruta> rutas = adyacencias.get(origen);
            for (Ruta r : rutas) {
                System.out.print(r.getDestino().getNombre() + "(" + r.getDistancia() + " km) ");
            }
            System.out.println();
        }
    }
}
