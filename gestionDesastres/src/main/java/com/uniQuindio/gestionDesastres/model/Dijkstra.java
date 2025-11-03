package com.uniQuindio.gestionDesastres.model;

import java.util.*;

public class Dijkstra {
    public static Map<Ubicacion, Float> calcularDistancias(GrafoDirigido grafo, Ubicacion origen) {
        Map<Ubicacion, Float> distancias = new HashMap<>();
        for (Ubicacion u : grafo.obtenerUbicaciones()) {
            distancias.put(u, Float.POSITIVE_INFINITY);
        }
        distancias.put(origen, 0f);

        PriorityQueue<Map.Entry<Ubicacion, Float>> cola = new PriorityQueue<>(Map.Entry.comparingByValue());
        cola.add(Map.entry(origen, 0f));

        while (!cola.isEmpty()) {
            Ubicacion actual = cola.poll().getKey();

            for (Ruta ruta : grafo.obtenerRutasDesde(actual)) {
                float nuevaDistancia = distancias.get(actual) + ruta.getDistancia();
                if (nuevaDistancia < distancias.get(ruta.getDestino())) {
                    distancias.put(ruta.getDestino(), nuevaDistancia);
                    cola.add(Map.entry(ruta.getDestino(), nuevaDistancia));
                }
            }
        }
        return distancias;
    }

    public static List<Ubicacion> caminoMasCorto(GrafoDirigido grafo, Ubicacion origen, Ubicacion destino) {
        Map<Ubicacion, Float> distancias = new HashMap<>();
        Map<Ubicacion, Ubicacion> previo = new HashMap<>();

        for (Ubicacion u : grafo.obtenerUbicaciones()) {
            distancias.put(u, Float.POSITIVE_INFINITY);
        }
        distancias.put(origen, 0f);

        PriorityQueue<Map.Entry<Ubicacion, Float>> cola = new PriorityQueue<>(Map.Entry.comparingByValue());
        cola.add(Map.entry(origen, 0f));

        while (!cola.isEmpty()) {
            Ubicacion actual = cola.poll().getKey();
            if (actual.equals(destino)) break;

            for (Ruta ruta : grafo.obtenerRutasDesde(actual)) {
                float nuevaDistancia = distancias.get(actual) + ruta.getDistancia();
                if (nuevaDistancia < distancias.get(ruta.getDestino())) {
                    distancias.put(ruta.getDestino(), nuevaDistancia);
                    previo.put(ruta.getDestino(), actual);
                    cola.add(Map.entry(ruta.getDestino(), nuevaDistancia));
                }
            }
        }

        // reconstruir camino
        List<Ubicacion> camino = new ArrayList<>();
        for (Ubicacion at = destino; at != null; at = previo.get(at)) {
            camino.add(at);
        }
        Collections.reverse(camino);
        return camino;
    }
}

