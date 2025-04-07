package com.sena.backendCosmeticosBellezaInfinita.controller;

import com.sena.backendCosmeticosBellezaInfinita.dto.UsuarioDTO;
import com.sena.backendCosmeticosBellezaInfinita.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping("/findbyId/{idDocumento}")
    public UsuarioDTO findbyId(@PathVariable String idDocumento) {
        return usuarioServices.findById(idDocumento);
    }
}
