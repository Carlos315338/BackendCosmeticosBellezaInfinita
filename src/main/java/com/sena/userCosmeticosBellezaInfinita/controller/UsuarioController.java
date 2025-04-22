package com.sena.userCosmeticosBellezaInfinita.controller;

import com.sena.userCosmeticosBellezaInfinita.dto.*;
import com.sena.userCosmeticosBellezaInfinita.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping("/obtenerListaUsuarios")
    public ResponseEntity<ApiResponse<Page<UsuarioDTO>>> obtenerUsuarios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<UsuarioDTO> usuarios = usuarioServices.findAll(page, size);

        return ResponseEntity.ok(ApiResponse.ok("Operacion Exitosa", usuarios));

    }

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
