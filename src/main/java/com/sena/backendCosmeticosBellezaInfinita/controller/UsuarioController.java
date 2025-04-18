package com.sena.backendCosmeticosBellezaInfinita.controller;

import com.sena.backendCosmeticosBellezaInfinita.dto.CambiarContrasenaDTO;
import com.sena.backendCosmeticosBellezaInfinita.dto.ConfirmacionUserDTO;
import com.sena.backendCosmeticosBellezaInfinita.dto.UsuarioDTO;
import com.sena.backendCosmeticosBellezaInfinita.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping("/findbyId/{idDocumento}")
    public UsuarioDTO findbyId(@PathVariable String idDocumento) {
        return usuarioServices.findById(idDocumento);
    }

    @PostMapping("/confirmacion-clave")
    public void confirmacionClave(@RequestBody ConfirmacionUserDTO dto) {

    }

    @PostMapping("/cambio-clave")
    public void cambioClave(@RequestBody CambiarContrasenaDTO dto) {

    }
}
