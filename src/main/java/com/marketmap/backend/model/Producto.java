package com.marketmap.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private String estado;
    private String imagenUrl;

    private Double latitud;
    private Double longitud;

    @ManyToOne
    @JoinColumn(name = "vendedor_id", referencedColumnName = "id")
    private Usuario vendedor;  // Relación Many-to-One
}
