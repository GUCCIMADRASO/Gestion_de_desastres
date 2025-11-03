package com.uniQuindio.gestionDesastres;

import com.uniQuindio.gestionDesastres.model.Ruta;
import com.uniQuindio.gestionDesastres.model.Ubicacion;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RutaTest {

    @Test
    void calcularPeso_bordesYLímites() {
        assertEquals(0, new Ruta(null, null, -1f).calcularPeso());
        assertEquals(0, new Ruta(null, null, 0f).calcularPeso());
        assertEquals(0, new Ruta(null, null, 4.9f).calcularPeso());

        assertEquals(1, new Ruta(null, null, 5f).calcularPeso());
        assertEquals(1, new Ruta(null, null, 9.99f).calcularPeso());

        assertEquals(2, new Ruta(null, null, 10f).calcularPeso());
        assertEquals(5, new Ruta(null, null, 26f).calcularPeso());

        assertEquals(10, new Ruta(null, null, 50f).calcularPeso());
        // distancia mayor a 50 sigue devolviendo 10 (límite del bucle)
        assertEquals(10, new Ruta(null, null, 51f).calcularPeso());
    }


}