package com.uniQuindio.gestionDesastres;

import com.uniQuindio.gestionDesastres.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GestionDesastresTest {
    private GestionDeDesastres gestion;

    @BeforeEach
    void setUp() {
        gestion = new GestionDeDesastres("Gestión de Quindío");
    }

    @Test
    void testAgregarYObtenerRecurso() {
        Recurso r = new Recurso("R1", "Kit de alimentos", TipoRecurso.ALIMENTO,40);
        assertTrue(gestion.agregarRecurso(r), "Debe permitir agregar un recurso válido");
        Recurso obtenido = gestion.obtenerRecursoPorId("R1");
        assertEquals("Kit de alimentos", obtenido.getNombre(), "El nombre del recurso debe coincidir");
    }

    @Test
    void testActualizarRecurso() {
        Recurso r = new Recurso("R2", "Caja de medicamentos", TipoRecurso.MEDICAMENTO,50);
        gestion.agregarRecurso(r);

        Recurso actualizado = new Recurso("R2", "Medicamentos antibióticos",TipoRecurso.MEDICAMENTO,50);
        assertTrue(gestion.actualizarRecurso(actualizado), "Debe permitir actualizar un recurso existente");
        assertEquals("Medicamentos antibióticos", gestion.obtenerRecursoPorId("R2").getNombre());
    }

    @Test
    void testEliminarRecurso() {
        Recurso r = new Recurso("R3", "Comida no perecedera", TipoRecurso.ALIMENTO,30);
        gestion.agregarRecurso(r);
        assertTrue(gestion.eliminarRecurso("R3"), "Debe eliminar correctamente el recurso existente");
        assertNull(gestion.obtenerRecursoPorId("R3"), "El recurso eliminado no debe encontrarse");
    }


    @Test
    void testAgregarYObtenerEquipo() {
        Equipo e = new Equipo("E1", 25,TipoEquipo.POLICIAS);
        assertTrue(gestion.agregarEquipo(e));
        Equipo obtenido = gestion.obtenerEquipoPorId("E1");
        assertEquals(TipoEquipo.POLICIAS, obtenido.getTipoEquipo());
    }

    @Test
    void testActualizarEquipo() {
        Equipo e = new Equipo("E2", 15,TipoEquipo.MEDICOS);
        gestion.agregarEquipo(e);

        Equipo actualizado = new Equipo("E2", 30,TipoEquipo.MEDICOS);
        assertTrue(gestion.actualizarEquipo(actualizado));
        assertEquals(30, gestion.obtenerEquipoPorId("E2").getIntegrantesDisponibles());
    }

    @Test
    void testEliminarEquipo() {
        Equipo e = new Equipo("E3", 10, TipoEquipo.BOMBEROS);
        gestion.agregarEquipo(e);
        assertTrue(gestion.eliminarEquipo("E3"));
        assertNull(gestion.obtenerEquipoPorId("E3"));
    }

    @Test
    void testAgregarYObtenerDesastre() {
        Ubicacion ubicacion = new Ubicacion("U1", "Armenia", "12", "8", TipoUbicacion.REFUGIO);
        Desastre d = new Desastre(5, "Inundación", "D1", TipoDesastre.INUNDACION, 1200, LocalDate.now(), ubicacion);
        assertTrue(gestion.agregarDesastre(d));
        Desastre obtenido = gestion.obtenerDesastrePorId("D1");
        assertEquals("Inundación", obtenido.getNombre());
    }

    @Test
    void testActualizarDesastre() {
        Ubicacion ubicacion = new Ubicacion("U2", "Pereira", "9", "4",TipoUbicacion.REFUGIO);
        Desastre d = new Desastre(3, "Incendio", "D2", TipoDesastre.INCENDIO, 400, LocalDate.now(), ubicacion);
        gestion.agregarDesastre(d);

        Desastre actualizado = new Desastre(4, "Incendio forestal", "D2", TipoDesastre.INCENDIO, 800, LocalDate.now(), ubicacion);
        assertTrue(gestion.actualizarDesastre(actualizado));
        assertEquals("Incendio forestal", gestion.obtenerDesastrePorId("D2").getNombre());
    }

    @Test
    void testEliminarDesastre() {
        Ubicacion ubicacion = new Ubicacion("U3", "Cali", "5", "10",TipoUbicacion.REFUGIO);
        Desastre d = new Desastre(2, "Derrumbe", "D3", TipoDesastre.DERRUMBE, 200, LocalDate.now(), ubicacion);
        gestion.agregarDesastre(d);
        assertTrue(gestion.eliminarDesastre("D3"));
        assertNull(gestion.obtenerDesastrePorId("D3"));
    }



}
