package com.uniQuindio.gestionDesastres.model;

public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String contrasena;
    private boolean sesionActiva = false;

    public Usuario(String id, String nombre, String email, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Usuario() {

    }

    public boolean iniciarSesion(String email, String contrasena) {
        if (this.email.equals(email) && this.contrasena.equals(contrasena)) {
            sesionActiva = true;
            System.out.println(nombre + " ha iniciado sesión.");
            return true;
        }
        System.out.println("Error de autenticación.");
        return false;
    }

    public void cerrarSesion() {
        if (sesionActiva) {
            sesionActiva = false;
            System.out.println(nombre + " ha cerrado sesión.");
        } else {
            System.out.println("No hay sesión activa.");
        }
    }

    public boolean isSesionActiva() {
        return sesionActiva;
    }
    public String getContrasena() {
        return contrasena;
    }
    public String getEmail() {
        return email;
    }
    public String getNombre() {
        return nombre;
    }
    public String getId() {
        return id;
    }
}
