package com.uniQuindio.gestionDesastres;


import com.uniQuindio.gestionDesastres.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario("U1", "Pepito", "pepito@uq.edu.co", "1234");
    }

    @Test
    void testIniciarSesion() {
        boolean resultado = usuario.iniciarSesion("pepito@uq.edu.co", "1234");
        assertTrue(resultado, "Debe permitir iniciar sesión con credenciales correctas");
        assertTrue(usuario.isSesionActiva(), "La sesión debe estar activa tras iniciar sesión");
    }


    @Test
    void testCerrarSesion() {
        usuario.iniciarSesion("pepito@uq.edu.co", "1234");
        usuario.cerrarSesion();
        assertFalse(usuario.isSesionActiva(), "Debe cerrar la sesión correctamente");
    }



}
