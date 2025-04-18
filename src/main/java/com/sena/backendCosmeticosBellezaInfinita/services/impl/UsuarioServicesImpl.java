package com.sena.backendCosmeticosBellezaInfinita.services.impl;

import com.sena.backendCosmeticosBellezaInfinita.dto.CambiarContrasenaDTO;
import com.sena.backendCosmeticosBellezaInfinita.dto.ConfirmacionUserDTO;
import com.sena.backendCosmeticosBellezaInfinita.dto.UsuarioDTO;
import com.sena.backendCosmeticosBellezaInfinita.entity.Usuario;
import com.sena.backendCosmeticosBellezaInfinita.mapper.UsuarioMapper;
import com.sena.backendCosmeticosBellezaInfinita.repository.UsuarioRepository;
import com.sena.backendCosmeticosBellezaInfinita.services.CognitoPasswordService;
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

    @Autowired
    private CognitoPasswordService cognitoPasswordService;

    public UsuarioDTO findById(String idDocumento) {
        Optional<Usuario> byId = usuarioRepository.findById(idDocumento);
        UsuarioDTO usuarioDTO = byId.map(usuario -> usuarioMapper.usuarioToUsuarioDTO(usuario)).orElse(null);
        return usuarioDTO;
    }

    @Override
    public void cambiarClaveEnPrimerLogin(ConfirmacionUserDTO confirmacionUser) {
        Optional<Usuario> byId = usuarioRepository.findById(confirmacionUser.getUsername());
        if (!byId.isPresent()) return;

        Usuario usuario = byId.get();
        cognitoPasswordService.cambiarClaveEnPrimerLogin(confirmacionUser.getUsername(), confirmacionUser.getTempPassword(), confirmacionUser.getNewPassword());
        usuario.setContrasenha(confirmacionUser.getNewPassword());
    }

    @Override
    public void cambiarContrasena(CambiarContrasenaDTO cambiarContrasena) {
        Optional<Usuario> byId = usuarioRepository.findById(cambiarContrasena.getIdUser());
        if (!byId.isPresent()) return;
        Usuario usuario = byId.get();
        cognitoPasswordService.cambiarContrasena(cambiarContrasena.getAccessToken(), cambiarContrasena.getContrasenaActual(), cambiarContrasena.getContrasenaNueva());
        usuario.setContrasenha(cambiarContrasena.getContrasenaNueva());
    }

}
