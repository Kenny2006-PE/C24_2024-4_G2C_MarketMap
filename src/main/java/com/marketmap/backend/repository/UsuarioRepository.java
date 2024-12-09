package com.marketmap.backend.repository;


import com.marketmap.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByDni(String dni);
}
