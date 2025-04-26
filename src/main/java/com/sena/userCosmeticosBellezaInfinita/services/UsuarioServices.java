package com.sena.userCosmeticosBellezaInfinita.services;

import org.springframework.data.domain.Page;

import com.sena.userCosmeticosBellezaInfinita.dto.*;

public interface UsuarioServices {
    public Page<UsuarioDTO> findAll(int page, int size);
    public UsuarioDTO findById(String idDocumento);
    public String cambiarClaveEnPrimerLogin(ConfirmacionUserDTO confirmacionUser );
    public String cambiarContrasena(CambiarContrasenaDTO cambiarContrasena);
    public String crearUsuario(CrearUsuarioDTO dto);
    public String cambiarContrasenaAdmin(CambiarClaveAdminDTO cambiarContrasena);
    public String eliminarUsuario(String id);
}
