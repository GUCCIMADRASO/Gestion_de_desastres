// src/main/java/com/uniQuindio/gestionDesastres/model/Usuario.java
package com.uniQuindio.gestionDesastres.model;

public class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String contrasena;

    public Usuario(String id, String nombre, String email, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Usuario() {}

    // Getters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getContrasena() { return contrasena; }
}
