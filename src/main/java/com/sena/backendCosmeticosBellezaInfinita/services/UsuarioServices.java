package com.sena.backendCosmeticosBellezaInfinita.services;

import com.sena.backendCosmeticosBellezaInfinita.dto.CambiarContrasenaDTO;
import com.sena.backendCosmeticosBellezaInfinita.dto.ConfirmacionUserDTO;
import com.sena.backendCosmeticosBellezaInfinita.dto.CrearUsuarioDTO;
import com.sena.backendCosmeticosBellezaInfinita.dto.UsuarioDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface UsuarioServices {
    public UsuarioDTO findById(String idDocumento);
    public String cambiarClaveEnPrimerLogin(ConfirmacionUserDTO confirmacionUser );
    public void cambiarContrasena(CambiarContrasenaDTO cambiarContrasena);
    public String crearUsuario(CrearUsuarioDTO dto);
}
