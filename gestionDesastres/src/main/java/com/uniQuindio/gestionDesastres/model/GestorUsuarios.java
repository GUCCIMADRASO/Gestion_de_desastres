package com.uniQuindio.gestionDesastres.model;

import java.io.*;

public class GestorUsuarios {
    private static final String ARCHIVO_USUARIOS = "datos" + File.separator + "usuarios.txt";

    public boolean registrarUsuario(String nombre, String email, String contrasena) {
        Usuario usuarioExistente = buscarUsuarioPorEmail(email);

        if (usuarioExistente != null) {
            System.out.println("Usuario ya existe con email: " + email);
            return false;
        }

        try {
            String id = "USR" + System.currentTimeMillis();
            Usuario usuario = new Usuario(id, nombre, email, contrasena);
            RegistroArchivo.guardarUsuario(usuario);
            System.out.println("Usuario registrado exitosamente: " + email);
            return true;
        } catch (Exception e) {
            System.err.println("Error al registrar usuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Usuario iniciarSesion(String email, String contrasena) {
        Usuario usuario = buscarUsuarioPorEmail(email);
        if (usuario != null && usuario.getContrasena().equals(contrasena)) {
            RegistroArchivo.registrarInicioSesion(usuario.getNombre(), usuario.getEmail());
            return usuario;
        }
        return null;
    }

    private Usuario buscarUsuarioPorEmail(String email) {
        File archivo = new File(ARCHIVO_USUARIOS);

        // Si el archivo no existe, retornar null inmediatamente
        if (!archivo.exists()) {
            System.out.println("Archivo no existe, no hay usuarios registrados");
            return null;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                // Ignorar líneas vacías
                if (linea.trim().isEmpty()) {
                    continue;
                }

                // Solo procesar líneas que empiecen con [USUARIO]
                if (linea.startsWith("[USUARIO]")) {
                    try {
                        // Remover el prefijo [USUARIO] y separar por |
                        String contenido = linea.replace("[USUARIO]", "").trim();
                        String[] partes = contenido.split("\\|");

                        if (partes.length >= 4) {
                            String id = partes[0].replace("ID:", "").trim();
                            String nombre = partes[1].replace("Nombre:", "").trim();
                            String emailEncontrado = partes[2].replace("Email:", "").trim();
                            String contrasena = partes[3].replace("Contraseña:", "").trim();

                            // Comparación exacta de email
                            if (emailEncontrado.equalsIgnoreCase(email)) {
                                System.out.println("Usuario encontrado: " + emailEncontrado);
                                return new Usuario(id, nombre, emailEncontrado, contrasena);
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Error al parsear línea: " + linea);
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado");
            return null;
        } catch (IOException e) {
            System.err.println("Error al leer archivo: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Usuario no encontrado con email: " + email);
        return null;
    }
}
