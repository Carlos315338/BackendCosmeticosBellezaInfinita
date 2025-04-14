package com.sena.backendCosmeticosBellezaInfinita.services.impl;

import com.sena.backendCosmeticosBellezaInfinita.dto.UsuarioDTO;
import com.sena.backendCosmeticosBellezaInfinita.entity.Usuario;
import com.sena.backendCosmeticosBellezaInfinita.mapper.UsuarioMapper;
import com.sena.backendCosmeticosBellezaInfinita.repository.UsuarioRepository;
import com.sena.backendCosmeticosBellezaInfinita.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicesImpl implements UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioDTO findById(String idDocumento) {
        Optional<Usuario> byId = usuarioRepository.findById(idDocumento);
        UsuarioDTO usuarioDTO = byId.map(usuario -> usuarioMapper.usuarioToUsuarioDTO(usuario)).orElse(null);
        return usuarioDTO;
    }
}
