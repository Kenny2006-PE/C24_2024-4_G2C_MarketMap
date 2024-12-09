package com.marketmap.backend.repository;

import com.marketmap.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar por DNI
    Usuario findByDni(String dni);

    // Buscar por correo (ya no se necesita findByUsername)
    Optional<Usuario> findByCorreo(String correo);
}
