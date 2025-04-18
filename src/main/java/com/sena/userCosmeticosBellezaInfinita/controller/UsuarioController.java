package com.sena.userCosmeticosBellezaInfinita.controller;

import com.sena.userCosmeticosBellezaInfinita.dto.*;
import com.sena.userCosmeticosBellezaInfinita.services.UsuarioServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuario")
@Tag(name = "tag_at_class_level", description = "Books related class level tag")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @Tag(name = "create")
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
    public ResponseEntity<ApiResponse<Void>> cambioClave(@RequestBody CambiarContrasenaDTO dto) {
        String response = usuarioServices.cambiarContrasena(dto);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/crearUsuario")
    public ResponseEntity<ApiResponse<Void>> crearUsuario(@RequestBody CrearUsuarioDTO dto) {
        String response = usuarioServices.crearUsuario(dto);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }

    @PostMapping("/cambio-clave-admin")
    public ResponseEntity<ApiResponse<Void>> cambioClaveAdmin(@RequestBody CambiarClaveAdminDTO dto) {
        String response = usuarioServices.cambiarContrasenaAdmin(dto);
        return ResponseEntity.ok(ApiResponse.ok(response));
    }
}
