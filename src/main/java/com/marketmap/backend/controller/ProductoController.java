package com.marketmap.backend.controller;

import com.marketmap.backend.model.Producto;
import com.marketmap.backend.model.Usuario;
import com.marketmap.backend.service.ProductoService;
import com.marketmap.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        // Obtener el usuario autenticado (vendedor)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String correo = authentication.getName();  // Obtener el correo del usuario autenticado

        Usuario vendedor = usuarioService.findByCorreo(correo);  // Buscar el vendedor por su correo
        if (vendedor != null) {
            producto.setVendedorId(vendedor.getId());  // Asociar el vendedor al producto
            return productoService.save(producto);  // Guardar el producto con el vendedor asignado
        }
        return null;  // Si el vendedor no se encuentra, retornar null
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable("id") Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body("El ID no puede ser nulo.");
        }

        Optional<Producto> producto = productoService.findById(id);
        if (producto.isPresent()) {
            return ResponseEntity.ok(producto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado.");
        }
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.findAll();  // Retornar todos los productos
    }
}
