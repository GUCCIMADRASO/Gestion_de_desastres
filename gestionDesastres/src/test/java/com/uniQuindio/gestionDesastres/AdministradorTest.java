package com.uniQuindio.gestionDesastres;



import com.uniQuindio.gestionDesastres.model.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdministradorTest {

    @Test
    void manejoRecursos_actualizaCantidadYImprime() {
        Administrador admin = new Administrador();
        Recurso recurso = new Recurso("1", "Agua", TipoRecurso.ALIMENTO, 10);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
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
    void definirRuta_devuelveRutaConPesoYImprime() {
        Administrador admin = new Administrador();
        Ubicacion o = new Ubicacion("O", "Origen", "Calle1", "Carrera1", null, null);
        Ubicacion d = new Ubicacion("D", "Destino", "Calle2", "Carrera2", null, null);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        Ruta ruta;
        try {
            ruta = admin.definirRuta(o, d, 12f);
        } finally {
            System.setOut(originalOut);
        }

        assertSame(o, ruta.getOrigen());
        assertSame(d, ruta.getDestino());
        assertEquals(12f, ruta.getDistancia(), 0.001f);
        assertEquals(2, ruta.calcularPeso());

        String salida = baos.toString();
        assertTrue(salida.contains("Ruta creada de"));
        assertTrue(salida.contains("Origen"));
        assertTrue(salida.contains("Destino"));
        assertTrue(salida.contains("12.0"));
    }

    @Test
    void asignarRecursos_consumoCorrectoSegunPrioridad() {
        Administrador admin = new Administrador();


        Desastre dAlta = new Desastre();
        dAlta.setNombre("Alta");
        dAlta.setMagnitud(4);
        dAlta.setPersonasAfectadas(100);
        dAlta.setEquiposAsignados(new ArrayList<>());

        Desastre dBaja = new Desastre();
        dBaja.setNombre("Baja");
        dBaja.setMagnitud(2);
        dBaja.setPersonasAfectadas(10);
        dBaja.setEquiposAsignados(new ArrayList<>());

        ColaPrioridad<Desastre> cola = new ColaPrioridad<>();
        cola.agregarElemento(dAlta);
        cola.agregarElemento(dBaja);

        Recurso alimento = new Recurso("2", "Comida", TipoRecurso.ALIMENTO, 500);
        Recurso medicamento = new Recurso("3", "Medicamento", TipoRecurso.MEDICAMENTO, 200);
        List<Recurso> recursos = new ArrayList<>();
        recursos.add(alimento);
        recursos.add(medicamento);

        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        try {
            admin.asignarRecursos(cola, recursos);
        } finally {
            System.setOut(originalOut);
        }

        assertEquals(500 - 300 - 30, alimento.getCantidad());
        assertEquals(200 - 100 - 10, medicamento.getCantidad());

        String salida = baos.toString();
        assertTrue(salida.contains("Asignación completada para el desastre"));
        assertTrue(salida.contains("Alta"));
        assertTrue(salida.contains("Baja"));
    }

}