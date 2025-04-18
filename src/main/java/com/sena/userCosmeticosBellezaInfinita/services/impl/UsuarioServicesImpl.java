package com.sena.userCosmeticosBellezaInfinita.services.impl;

import com.sena.userCosmeticosBellezaInfinita.dto.*;
import com.sena.userCosmeticosBellezaInfinita.entity.Rol;
import com.sena.userCosmeticosBellezaInfinita.entity.Usuario;
import com.sena.userCosmeticosBellezaInfinita.exception.RolNoEncontradoException;
import com.sena.userCosmeticosBellezaInfinita.exception.UsuarioNoEncontradoException;
import com.sena.userCosmeticosBellezaInfinita.mapper.UsuarioMapper;
import com.sena.userCosmeticosBellezaInfinita.repository.RolRepository;
import com.sena.userCosmeticosBellezaInfinita.repository.UsuarioRepository;
import com.sena.userCosmeticosBellezaInfinita.services.CognitoPasswordService;
import com.sena.userCosmeticosBellezaInfinita.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.InvalidParameterException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UsernameExistsException;

import java.util.Optional;

@Service
public class UsuarioServicesImpl implements UsuarioServices {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private CognitoPasswordService cognitoPasswordService;

    public UsuarioDTO findById(String idDocumento) {
        try {

            Usuario byId = usuarioRepository.findById(idDocumento).orElseThrow(() -> new UsuarioNoEncontradoException("El usuario con ID " + idDocumento + " no existe") );
            UsuarioDTO usuarioDTO =  usuarioMapper.usuarioToUsuarioDTO(byId);
            return usuarioDTO;
        } catch (UsuarioNoEncontradoException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String cambiarClaveEnPrimerLogin(ConfirmacionUserDTO confirmacionUser) {

        try {
            Usuario usuario = usuarioRepository.findById(confirmacionUser.getUsername()).orElseThrow(() -> new UsuarioNoEncontradoException("El usuario con ID " + confirmacionUser.getUsername() + " no existe") );;
            cognitoPasswordService.cambiarClaveEnPrimerLogin(confirmacionUser.getUsername(), confirmacionUser.getTempPassword(), confirmacionUser.getNewPassword());
            usuario.setContrasenha(confirmacionUser.getNewPassword());
            cognitoPasswordService.cerrarSesionesGlobales(confirmacionUser.getUsername());
            return "Cambio de contraseña exitosa";
        } catch (UsuarioNoEncontradoException e) {
            return e.getMessage();
        } catch (CognitoIdentityProviderException e) {
            return "Servicio no responde";
        }
    }

    @Override
    public void cambiarContrasena(CambiarContrasenaDTO cambiarContrasena) {
        Optional<Usuario> byId = usuarioRepository.findById(cambiarContrasena.getIdUser());
        if (!byId.isPresent()) return;
        Usuario usuario = byId.get();
        cognitoPasswordService.cambiarContrasena(cambiarContrasena.getAccessToken(), cambiarContrasena.getContrasenaActual(), cambiarContrasena.getContrasenaNueva());
        usuario.setContrasenha(cambiarContrasena.getContrasenaNueva());
        cognitoPasswordService.cerrarSesionesGlobales(cambiarContrasena.getIdUser());
    }

    @Override
    public String crearUsuario(CrearUsuarioDTO dto) {

        try {

            Rol byId = rolRepository.findById(dto.getRolId()).orElseThrow(() -> new RolNoEncontradoException("El Rol con el ID "+ dto.getRolId()  +" No eciste"));

            Usuario usuarioNuevo =  new Usuario();
            usuarioNuevo.setNombreUsuario(dto.getUserName());
            usuarioNuevo.setIdUsuario(dto.getUserId());
            usuarioNuevo.setContrasenha(dto.getUserId());
            usuarioNuevo.setRol(byId);

            Usuario save = usuarioRepository.save(usuarioNuevo);
            cognitoPasswordService.crearUsuario(save.getIdUsuario(), dto.getEmail(), save.getNombreUsuario(), dto.getPhoneNumber());

            return "Usuario creado exitosamente";
        } catch (RolNoEncontradoException e) {
            return e.getMessage();
        }catch (UsernameExistsException e) {
            return e.getMessage();
        } catch (InvalidParameterException e) {
            return e.getMessage();
        } catch (CognitoIdentityProviderException e) {
            return e.getMessage();
        }
    }

    @Override
    public void cambiarContrasenaAdmin(CambiarClaveAdminDTO cambiarContrasena) {
        Optional<Usuario> byId = usuarioRepository.findById(cambiarContrasena.getIdUser());
        if (!byId.isPresent()) return;
        Usuario usuario = byId.get();
        cognitoPasswordService.cambiarClaveComoAdmin(cambiarContrasena.getIdUser(), cambiarContrasena.getContrasenaTemporal());
        usuario.setContrasenha(cambiarContrasena.getContrasenaTemporal());
        cognitoPasswordService.cerrarSesionesGlobales(cambiarContrasena.getIdUser());
    }
}
