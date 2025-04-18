package com.sena.backendCosmeticosBellezaInfinita.controller;

import com.sena.backendCosmeticosBellezaInfinita.dto.*;
import com.sena.backendCosmeticosBellezaInfinita.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping("/findbyId/{idDocumento}")
    public ResponseEntity<ApiResponse<UsuarioDTO>> findbyId(@PathVariable String idDocumento) {
        UsuarioDTO byId = usuarioServices.findById(idDocumento);
        return ResponseEntity.ok(ApiResponse.ok("Usuario encontrado", byId));
    }

    @PostMapping("/confirmacion-clave")
    public ResponseEntity<ApiResponse<Void>> confirmacionClave(@RequestBody ConfirmacionUserDTO dto) {
        usuarioServices.cambiarClaveEnPrimerLogin(dto);
        return ResponseEntity.ok(ApiResponse.ok("Usuario encontrado"));
    }

    @PostMapping("/cambio-clave")
    public void cambioClave(@RequestBody CambiarContrasenaDTO dto) {
        usuarioServices.cambiarContrasena(dto);
    }

    @PostMapping("/crearUsuario")
    public void crearUsuario(@RequestBody CrearUsuarioDTO dto) {
        usuarioServices.crearUsuario(dto);
    }
}
