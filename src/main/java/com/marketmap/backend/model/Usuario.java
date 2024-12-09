package com.marketmap.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String correo;
    private String dni;
    private String numero;
    private String password;

    @Enumerated(EnumType.STRING)
    private Estado estado;

    public enum Estado {
        ACTIVO, BANEADO
    }
}
