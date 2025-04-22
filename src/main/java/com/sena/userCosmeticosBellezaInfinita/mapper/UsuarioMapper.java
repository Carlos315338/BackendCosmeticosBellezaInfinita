package com.sena.userCosmeticosBellezaInfinita.mapper;

import com.sena.userCosmeticosBellezaInfinita.dto.UsuarioDTO;
import com.sena.userCosmeticosBellezaInfinita.entity.Usuario;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {RolMapper.class})
public interface UsuarioMapper {

    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
    Usuario usuarioDtoDTOToUsuario(UsuarioDTO dto);

    List<UsuarioDTO> listUsuarioToListUsuarioDTO(List<Usuario> usuarios);
    List<Usuario> listUsuarioDTOToListUsuario(List<UsuarioDTO> usuariosdto);
}
