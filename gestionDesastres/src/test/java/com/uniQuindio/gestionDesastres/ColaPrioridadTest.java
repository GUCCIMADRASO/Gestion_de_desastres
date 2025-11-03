package com.uniQuindio.gestionDesastres;

import com.uniQuindio.gestionDesastres.model.ColaPrioridad;
import com.uniQuindio.gestionDesastres.model.Desastre;
import com.uniQuindio.gestionDesastres.model.TipoDesastre;
import com.uniQuindio.gestionDesastres.model.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class ColaPrioridadTest {
    private ColaPrioridad<Desastre> cola;
    private Ubicacion cali, armenia, pereira;

    @BeforeEach
    void setUp() {
        cola = new ColaPrioridad<>();

        // Crear ubicaciones
        cali = new Ubicacion("U1", "Cali", "5", "10");
        armenia = new Ubicacion("U2", "Armenia", "12", "8");
        pereira = new Ubicacion("U3", "Pereira", "9", "4");

        // Crear desastres asociados a ubicaciones
        Desastre d1 = new Desastre(5, "Inundación", "D1", TipoDesastre.INUNDACION, 800, LocalDate.now(), cali);
        Desastre d2 = new Desastre(3, "Incendio", "D2", TipoDesastre.INCENDIO, 400, LocalDate.now(), armenia);
        Desastre d3 = new Desastre(2, "Derrumbe", "D3", TipoDesastre.DERRUMBE, 120, LocalDate.now(), pereira);

        cola.agregarElemento(d1);
        cola.agregarElemento(d2);
        cola.agregarElemento(d3);
    }


    @Test
    void testColaNoVacia() {
        assertFalse(cola.estaVacia(), "La cola no debe estar vacía al iniciar");
    }
    @Test
    void testMostrarCola() {
        String salida = cola.mostrarCola();
        assertTrue(salida.contains("Cola de prioridad actual:"), "Debe contener el encabezado");
        assertTrue(salida.contains("Inundación"), "Debe incluir el desastre 'Inundación'");
        assertTrue(salida.contains("Incendio"), "Debe incluir el desastre 'Incendio'");
        assertTrue(salida.contains("Derrumbe"), "Debe incluir el desastre 'Derrumbe'");
    }


    @Test
    void testAtenderSiguienteDesastre() {
        Desastre siguiente = cola.atenderSiguiente();
        assertEquals("Inundación", siguiente.getNombre(), "Debe atender primero la Inundación (prioridad Alta)");
    }

    @Test
    void testOrdenDePrioridad() {
        Desastre primero = cola.atenderSiguiente();
        Desastre segundo = cola.atenderSiguiente();
        assertTrue(primero.asignarNivelPrioridad() <= segundo.asignarNivelPrioridad(), "El primer desastre debe tener prioridad igual o mayor al siguiente");
    }
}
