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
    private UsuarioRepository usuarioRepository;  // Repositorio de Usuario para obtener los datos del vendedor

    public Producto save(Producto producto) {
        // Obtener el usuario autenticado (vendedor)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();  // Usamos el nombre de usuario para buscar el vendedor

        // Obtener los detalles del vendedor
        Optional<Usuario> vendedor = usuarioRepository.findByCorreo(username);
        if (vendedor.isPresent()) {
            producto.setVendedorId(vendedor.get().getId()); // Asignar el vendedor al producto
        } else {
            throw new RuntimeException("Usuario no encontrado");  // En caso de que el vendedor no exista
        }

        return productoRepository.save(producto);  // Guardamos el producto con el vendedor asociado
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
}
