package com.sena.backendCosmeticosBellezaInfinita.mapper;

import com.sena.backendCosmeticosBellezaInfinita.dto.UsuarioDTO;
import com.sena.backendCosmeticosBellezaInfinita.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UsuarioMapper {

    @Mapping(source = "idUsuario", target = "idUsuario")
    @Mapping(source = "nombreUsuario", target = "nombreUsuario")
    @Mapping(source = "contrasenha", target = "contrasenha")
    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    Usuario usuarioDtoDTOToUsuario(UsuarioDTO dto);
}