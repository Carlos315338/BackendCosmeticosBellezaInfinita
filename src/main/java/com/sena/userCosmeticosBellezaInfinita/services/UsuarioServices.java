package com.sena.userCosmeticosBellezaInfinita.services;

import com.sena.userCosmeticosBellezaInfinita.dto.*;

public interface UsuarioServices {
    public UsuarioDTO findById(String idDocumento);
    public String cambiarClaveEnPrimerLogin(ConfirmacionUserDTO confirmacionUser );
    public void cambiarContrasena(CambiarContrasenaDTO cambiarContrasena);
    public String crearUsuario(CrearUsuarioDTO dto);
    public void cambiarContrasenaAdmin(CambiarClaveAdminDTO cambiarContrasena);
}
