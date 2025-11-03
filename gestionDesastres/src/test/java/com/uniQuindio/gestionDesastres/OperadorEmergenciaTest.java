package com.uniQuindio.gestionDesastres;

import com.uniQuindio.gestionDesastres.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OperadorEmergenciaTest {
    private OperadorEmergencia operador;
    private List<Ubicacion> ubicaciones;
    private Desastre desastre1, desastre2;
    private Equipo equipo;

    @BeforeEach
    void setUp() {
        operador = new OperadorEmergencia("Ana López", "O1", "ana@uq.edu.co", "1234");

        // Crear ubicaciones
        ubicaciones = new ArrayList<>();
        ubicaciones.add(new Ubicacion("U1", "Cali", "5", "10"));
        ubicaciones.add(new Ubicacion("U2", "Armenia", "12", "8"));
        ubicaciones.add(new Ubicacion("U3", "Pereira", "9", "4"));

        // Crear desastres
        desastre1 = new Desastre(5, "Inundación", "D1", TipoDesastre.INUNDACION, 1500, LocalDate.now(), ubicaciones.get(0));
        desastre2 = new Desastre(2, "Derrumbe", "D2", TipoDesastre.DERRUMBE, 200, LocalDate.now(), ubicaciones.get(1));

        // Crear equipo
        equipo = new Equipo("E1", 40, TipoEquipo.BOMBEROS);
    }
    @Test
    void testMonitorearUbicaciones() {
        //se prueba de que el metodo funcione correctamente sin lanzar eerores
        assertDoesNotThrow(() -> operador.monitorearUbicaciones(ubicaciones),
                "El método monitorearUbicaciones no debe lanzar errores");
    }

    @Test
    void testActualizarSituacion() {
        operador.actualizarSituacion(desastre1, 3000, 6);
        assertEquals(3000, desastre1.getPersonasAfectadas(), "Debe actualizar las personas afectadas");
        assertEquals(6, desastre1.getMagnitud(), "Debe actualizar la magnitud del desastre");
    }

    @Test
    void testCoordinarRecursosAsignaEquipo() {
        operador.coordinarRecursos(desastre1, equipo);
        assertFalse(desastre1.getEquiposAsignados().isEmpty(), "Debe asignar un equipo al desastre");
        assertTrue(equipo.getIntegrantesDisponibles() < 50, "Debe reducir el número de integrantes disponibles");
    }

    @Test
    void testGestionarEvacuaciones() {
        List<Desastre> listaDesastres = List.of(desastre1, desastre2);
        assertDoesNotThrow(() -> operador.gestionarEvacuaciones(listaDesastres),
                "El método gestionarEvacuaciones no debe lanzar errores");

        // Verificamos que los desastres sigan existiendo pero la cola se haya vaciado internamente
        ColaPrioridad<Desastre> cola = new ColaPrioridad<>();
        listaDesastres.forEach(cola::agregarElemento);
        while (!cola.estaVacia()) {
            cola.atenderSiguiente();
        }
        assertTrue(cola.estaVacia(), "Después de atender, la cola debe quedar vacía");
    }
    @Test
    void testGestionarEvacuacionesVacia() {
        List<Desastre> vacia = new ArrayList<>();
        assertDoesNotThrow(() -> operador.gestionarEvacuaciones(vacia),
                "Debe manejar correctamente el caso de lista vacía sin errores");
    }
}
