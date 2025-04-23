package com.sena.userCosmeticosBellezaInfinita.mapper;

import com.sena.userCosmeticosBellezaInfinita.dto.RolDTO;
import com.sena.userCosmeticosBellezaInfinita.dto.RolSelectDTO;
import com.sena.userCosmeticosBellezaInfinita.entity.Rol;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ModuloMapper.class})
public interface RolMapper {

    RolDTO rolToRolDTO(Rol rol);
    Rol rolDTOToRol(RolDTO rolDTO);

    @Mapping(source = "idRol", target = "idRol")
    @Mapping(source = "nombreRol", target = "nombreRol")
    @BeanMapping(ignoreByDefault = true)
    RolSelectDTO rolToRolSelectDTO(Rol rol);

    List<RolSelectDTO> listRolToListRolSelectDTO(List<Rol> roleList);
}
