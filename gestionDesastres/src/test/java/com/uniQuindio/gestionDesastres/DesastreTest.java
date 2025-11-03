package com.uniQuindio.gestionDesastres;

import com.uniQuindio.gestionDesastres.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class DesastreTest {
    private Desastre desastre1, desastre2, desastre3;
    private Equipo equipo1, equipo2;
    private Ubicacion cali, armenia, pereira;

    @BeforeEach
    void setUp() {
        // Crear ubicaciones
        cali = new Ubicacion("U1", "Cali", "5", "10");
        armenia = new Ubicacion("U2", "Armenia", "12", "8");
        pereira = new Ubicacion("U3", "Pereira", "9", "4");

        // Crear desastres
        desastre1 = new Desastre(5, "Inundación", "D1", TipoDesastre.INUNDACION, 2000, LocalDate.now(), cali);
        desastre2 = new Desastre(3, "Incendio", "D2", TipoDesastre.INCENDIO, 700, LocalDate.now(), armenia);
        desastre3= new Desastre(1, "Derrumbe", "D3", TipoDesastre.DERRUMBE, 100, LocalDate.now(), pereira);

        // Crear equipos simulados
        equipo1 = new Equipo("E1",50 , TipoEquipo.POLICIAS);
        equipo2 = new Equipo("E2",5 , TipoEquipo.MEDICOS);
    }
    @Test
    void testAsignarPrioridadAlta() {
        assertEquals("Alta", desastre1.asignarPrioridad(), "Debe ser prioridad Alta por magnitud y personas afectadas");
    }

    @Test
    void testAsignarPrioridadMedia() {
        assertEquals("Media", desastre2.asignarPrioridad(), "Debe ser prioridad Media");
    }

    @Test
    void testAsignarPrioridadBaja() {
        assertEquals("Baja", desastre3.asignarPrioridad(), "Debe ser prioridad Baja");
    }

    @Test
    void testNivelPrioridad() {
        assertTrue(desastre1.asignarNivelPrioridad() < desastre2.asignarNivelPrioridad(),
                "Alta debe tener un número de prioridad menor que Media");
        assertTrue(desastre2.asignarNivelPrioridad() < desastre3.asignarNivelPrioridad(),
                "Media debe tener un número de prioridad menor que Baja");
    }

    @Test
    void testCompareToOrdenaCorrectamente() {
        // compareTo devuelve negativo si el primero tiene mayor prioridad
        assertTrue(desastre1.compareTo(desastre2) < 0, "Inundación debe ir antes que Incendio");
        assertTrue(desastre2.compareTo(desastre3) < 0, "Incendio debe ir antes que Derrumbe");
    }

    @Test
    void testAsignarEquipoConSuficientePersonal() {
        int antes = equipo1.getIntegrantesDisponibles();
        desastre1.asignarEquipo(equipo1);

        assertTrue(equipo1.getIntegrantesDisponibles() < antes,
                "Debe reducir el número de integrantes disponibles del equipo");
        assertFalse(desastre1.getEquiposAsignados().isEmpty(), "El equipo debe haberse asignado al desastre");
    }

    @Test
    void testAsignarEquipoSinSuficientePersonal() {
        int antes = equipo2.getIntegrantesDisponibles();
        desastre2.asignarEquipo(equipo2);

        assertEquals(antes, equipo2.getIntegrantesDisponibles(),
                "No debe cambiar los integrantes disponibles si no alcanza el personal");
        assertTrue(desastre2.getEquiposAsignados().isEmpty(),
                "No debe agregarse el equipo si no tiene personal suficiente");
    }


}




