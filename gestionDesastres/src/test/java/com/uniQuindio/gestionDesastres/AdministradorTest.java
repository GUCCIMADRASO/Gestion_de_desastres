package com.uniQuindio.gestionDesastres;

import com.uniQuindio.gestionDesastres.model.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class AdministradorTest {

    @Test
    void manejoRecursos_actualizaCantidadYImprime() {
        Administrador admin = new Administrador();
        Recurso recurso = new Recurso("1", "Agua", TipoRecurso.ALIMENTO, 10);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        try {
            admin.manejoRecursos(recurso, 5);
        } finally {
            System.setOut(originalOut);
        }

        assertEquals(15, recurso.getCantidad());
        String salida = baos.toString();
        assertTrue(salida.contains("Recurso actualizado"));
        assertTrue(salida.contains("Agua"));
    }

    @Test
    void definirRuta_usaDijkstraYImprimeCamino() {
        Administrador admin = new Administrador();

        // Crear ubicaciones
        Ubicacion a = new Ubicacion("A", "Base", "C1", "R1");
        Ubicacion b = new Ubicacion("B", "Hospital", "C2", "R2");
        Ubicacion c = new Ubicacion("C", "Escuela", "C3", "R3");
        Ubicacion d = new Ubicacion("D", "Zona Segura", "C4", "R4");

        // Crear grafo
        GrafoDirigido grafo = new GrafoDirigido();
        grafo.agregarRuta(new Ruta(a, b, 5f));
        grafo.agregarRuta(new Ruta(b, c, 3f));
        grafo.agregarRuta(new Ruta(a, c, 10f));
        grafo.agregarRuta(new Ruta(c, d, 2f));
        grafo.agregarRuta(new Ruta(b, d, 7f));

        // Capturar salida de consola
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        Ruta ruta;
        try {
            ruta = admin.definirRuta(grafo, a, d);
        } finally {
            System.setOut(originalOut);
        }

        assertNotNull(ruta);
        assertEquals(a, ruta.getOrigen());
        assertEquals(d, ruta.getDestino());
        assertEquals(10f, ruta.getDistancia(), 0.001f); // Base -> Hospital (5) + Hospital -> C (3) + C -> D (2)
        assertEquals(2, ruta.calcularPeso());

        String salida = baos.toString();
        assertTrue(salida.contains("Ruta óptima creada"));
        assertTrue(salida.contains("Base"));
        assertTrue(salida.contains("Zona Segura"));
        assertTrue(salida.contains("Camino más corto"));
        assertTrue(salida.contains("Base → Hospital → Escuela → Zona Segura"));
    }

    @Test
    void asignarRecursos_asignaSegunPrioridad() {
        Administrador admin = new Administrador();

        // Crear desastres
        Desastre dAlta = new Desastre();
        dAlta.setNombre("Incendio");
        dAlta.setMagnitud(4);
        dAlta.setPersonasAfectadas(100);
        dAlta.setEquiposAsignados(new ArrayList<>());

        Desastre dBaja = new Desastre();
        dBaja.setNombre("Inundación");
        dBaja.setMagnitud(2);
        dBaja.setPersonasAfectadas(20);
        dBaja.setEquiposAsignados(new ArrayList<>());

        ColaPrioridad<Desastre> cola = new ColaPrioridad<>();
        cola.agregarElemento(dAlta);
        cola.agregarElemento(dBaja);

        // Crear recursos
        Recurso comida = new Recurso("2", "Comida", TipoRecurso.ALIMENTO, 500);
        Recurso medicina = new Recurso("3", "Medicamento", TipoRecurso.MEDICAMENTO, 200);
        List<Recurso> recursos = Arrays.asList(comida, medicina);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(baos));

        try {
            admin.asignarRecursos(cola, recursos);
        } finally {
            System.setOut(originalOut);
        }

        assertEquals(500 - (100 * 3) - (20 * 3), comida.getCantidad());
        assertEquals(200 - (100) - (20), medicina.getCantidad());

        String salida = baos.toString();
        assertTrue(salida.contains("Asignación completada"));
        assertTrue(salida.contains("Incendio"));
        assertTrue(salida.contains("Inundación"));
    }
}
