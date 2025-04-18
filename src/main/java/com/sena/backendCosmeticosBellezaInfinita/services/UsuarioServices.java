package com.sena.backendCosmeticosBellezaInfinita.services;

import com.sena.backendCosmeticosBellezaInfinita.dto.CambiarContrasenaDTO;
import com.sena.backendCosmeticosBellezaInfinita.dto.ConfirmacionUserDTO;
import com.sena.backendCosmeticosBellezaInfinita.dto.UsuarioDTO;

public interface UsuarioServices {
    public UsuarioDTO findById(String idDocumento);
    public void cambiarClaveEnPrimerLogin(ConfirmacionUserDTO confirmacionUser );
    public void cambiarContrasena(CambiarContrasenaDTO cambiarContrasena);
}
