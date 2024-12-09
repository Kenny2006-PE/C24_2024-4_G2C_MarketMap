package com.marketmap.backend.service;

import com.marketmap.backend.model.Usuario;
import com.marketmap.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findByDni(String dni) {
        return usuarioRepository.findByDni(dni);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        // Usamos orElseThrow para lanzar una excepción si no se encuentra el usuario
        Usuario usuario = usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));

        // Retornamos un objeto User de Spring Security con las credenciales del usuario
        return new User(usuario.getCorreo(), usuario.getPassword(), Collections.emptyList());
    }

    public Usuario findByCorreo(String correo) {
        // Usamos orElseThrow para manejar el Optional de manera más directa
        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con correo: " + correo));
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);  // Retorna el usuario o null si no se encuentra
    }
}
