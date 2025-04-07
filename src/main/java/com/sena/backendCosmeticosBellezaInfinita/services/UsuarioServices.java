package com.sena.backendCosmeticosBellezaInfinita.services;

import com.sena.backendCosmeticosBellezaInfinita.dto.UsuarioDTO;

public interface UsuarioServices {
    public UsuarioDTO findById(String idDocumento);
}
