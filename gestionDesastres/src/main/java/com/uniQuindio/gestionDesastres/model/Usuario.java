package com.uniQuindio.gestionDesastres.model;

public abstract class Usuario {

    protected String nombre;
    protected String id;
    protected String email;
    protected String contrasena;


    public Usuario(String nombre, String id, String email, String contrasena) {
        this.nombre = nombre;
        this.id = id;
        this.email = email;
        this.contrasena = contrasena;
    }
    public Usuario() {
    }

    // Métodos comunes
    public boolean iniciarSesion(String email, String contrasena) {
        return this.email.equals(email) && this.contrasena.equals(contrasena);
    }

    public void cerrarSesion() {
        System.out.println(nombre + " ha cerrado sesión.");
    }


    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    public void getContrasena() {
        return;
    }
}
