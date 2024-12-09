package com.marketmap.backend.controller;


import com.marketmap.backend.model.Usuario;
import com.marketmap.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @GetMapping("/{dni}")
    public Usuario obtenerUsuario(@PathVariable String dni) {
        return usuarioService.findByDni(dni);
    }
}
