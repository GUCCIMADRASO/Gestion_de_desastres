package com.uniQuindio.gestionDesastres;

import com.uniQuindio.gestionDesastres.model.Dijkstra;
import com.uniQuindio.gestionDesastres.model.GrafoDirigido;
import com.uniQuindio.gestionDesastres.model.Ruta;
import com.uniQuindio.gestionDesastres.model.Ubicacion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class DijkstraTest {

    @Test
    void calcularDistancias() {
        Ubicacion a = new Ubicacion("A","A","C","R");
        Ubicacion b = new Ubicacion("B","B","C","R");
        Ubicacion c = new Ubicacion("C","C","C","R");
        Ubicacion d = new Ubicacion("D","D","C","R");

        Ruta ab = new Ruta(a, b, 5f);
        Ruta ac = new Ruta(a, c, 10f);
        Ruta bc = new Ruta(b, c, 4f);
        Ruta bd = new Ruta(b, d, 11f);
        Ruta cd = new Ruta(c, d, 3f);

        GrafoDirigido grafo = new GrafoDirigido();
        grafo.agregarRuta(ab);
        grafo.agregarRuta(ac);
        grafo.agregarRuta(bc);
        grafo.agregarRuta(bd);
        grafo.agregarRuta(cd);

        Map<Ubicacion, Float> dist = Dijkstra.calcularDistancias(grafo, a);

        assertEquals(0f, dist.get(a), 0.001f);
        assertEquals(5f, dist.get(b), 0.001f);
        assertEquals(9f, dist.get(c), 0.001f);
        assertEquals(12f, dist.get(d), 0.001f);
    }

}