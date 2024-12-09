package com.marketmap.backend.dto;

public class JwtResponse {
    private String token;
    private String nombre;
    private String correo;

    // Constructor con token
    public JwtResponse(String token) {
        this.token = token;
    }

    // Getters y Setters
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
