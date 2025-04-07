package com.sena.backendCosmeticosBellezaInfinita.mapper;

import com.sena.backendCosmeticosBellezaInfinita.dto.UsuarioDTO;
import com.sena.backendCosmeticosBellezaInfinita.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {

    //@Mapping(target = "contrasenha", ignore = true)
    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
    Usuario usuarioDtoDTOToUsuario(UsuarioDTO dto);
}