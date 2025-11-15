package com.uniQuindio.gestionDesastres;

import com.uniQuindio.gestionDesastres.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class DijkstraTest {

    @Test
    void calcularDistancias() {
        Ubicacion a = new Ubicacion("A","A","C","R", TipoUbicacion.CENTRO_AYUDA);
        Ubicacion b = new Ubicacion("B","B","C","R",TipoUbicacion.REFUGIO);
        Ubicacion c = new Ubicacion("C","C","C","R",TipoUbicacion.CENTRO_AYUDA);
        Ubicacion d = new Ubicacion("D","D","C","R",TipoUbicacion.REFUGIO);

        Ruta ab = new Ruta(a, b, 5f);
        Ruta ac = new Ruta(a, c, 10f);
        Ruta bc = new Ruta(b, c, 4f);
        Ruta bd = new Ruta(b, d, 11f);
        Ruta cd = new Ruta(c, d, 3f);

        GrafoNoDirigido grafo = new GrafoNoDirigido();
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