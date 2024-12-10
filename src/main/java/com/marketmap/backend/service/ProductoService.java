package com.marketmap.backend.service;

import com.marketmap.backend.model.Producto;
import com.marketmap.backend.model.Usuario;
import com.marketmap.backend.repository.ProductoRepository;
import com.marketmap.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Producto save(Producto producto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Optional<Usuario> vendedor = usuarioRepository.findByCorreo(username);

        if (vendedor.isPresent()) {
            producto.setVendedorId(vendedor.get().getId());
        } else {
            throw new RuntimeException("Usuario no encontrado");
        }

        return productoRepository.save(producto);
    }

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    public Optional<Producto> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID proporcionado es nulo.");
        }
        return productoRepository.findById(id);
    }

    // Nuevo método para buscar productos por nombre
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}
