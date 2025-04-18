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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.cognitoidentityprovider.model.CognitoIdentityProviderException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.InvalidParameterException;
import software.amazon.awssdk.services.cognitoidentityprovider.model.UsernameExistsException;

import java.util.Optional;

@Slf4j
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
    public String cambiarContrasena(CambiarContrasenaDTO cambiarContrasena) {
        try {
            Usuario usuario = usuarioRepository.findById(cambiarContrasena.getIdUser()).orElseThrow(() -> new UsuarioNoEncontradoException("El usuario con ID " + cambiarContrasena.getIdUser() + " no existe") );
            cognitoPasswordService.cambiarContrasena(cambiarContrasena.getAccessToken(), cambiarContrasena.getContrasenaActual(), cambiarContrasena.getContrasenaNueva());
            usuario.setContrasenha(cambiarContrasena.getContrasenaNueva());
            cognitoPasswordService.cerrarSesionesGlobales(cambiarContrasena.getIdUser());
            return "Cambnio de contraseña correctamente ";
        } catch (UsuarioNoEncontradoException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
            log.info("Error crearUsuario usuario {}  : {}", dto.getRolId(), e.getMessage());
            return e.getMessage();
        }catch (UsernameExistsException e) {
            log.info("Error crearUsuario usuario {}  : {}", dto.getRolId(), e.getMessage());
            return e.getMessage();
        } catch (InvalidParameterException e) {
            log.info("Error crearUsuario usuario {}  : {}", dto.getRolId(), e.getMessage());
            return e.getMessage();
        } catch (CognitoIdentityProviderException e) {
            log.info("Error crearUsuario usuario {}  : {}", dto.getRolId(), e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public String cambiarContrasenaAdmin(CambiarClaveAdminDTO cambiarContrasena) {
        try {
            Usuario Usuario = usuarioRepository.findById(cambiarContrasena.getIdUser()).orElseThrow(() -> new RolNoEncontradoException("El Rol con el ID "+ cambiarContrasena.getIdUser()  +" No eciste"));
            cognitoPasswordService.cambiarClaveComoAdmin(cambiarContrasena.getIdUser(), cambiarContrasena.getContrasenaTemporal());
            Usuario.setContrasenha(cambiarContrasena.getContrasenaTemporal());
            cognitoPasswordService.cerrarSesionesGlobales(cambiarContrasena.getIdUser());
            usuarioRepository.save(Usuario);
            return "Usuario creado exitosamente";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
