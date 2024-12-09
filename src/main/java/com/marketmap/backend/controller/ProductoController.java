package com.marketmap.backend.controller;


import com.marketmap.backend.model.Producto;
import com.marketmap.backend.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:3000") // Permite solicitudes desde tu frontend
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        System.out.println("Producto recibido en el backend: " + producto);
        return productoService.save(producto);
    }



    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.findAll();
    }
}

