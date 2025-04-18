package com.sena.userCosmeticosBellezaInfinita.mapper;

import com.sena.userCosmeticosBellezaInfinita.dto.RolDTO;
import com.sena.userCosmeticosBellezaInfinita.entity.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {ModuloMapper.class})
public interface RolMapper {
    RolDTO rolToRolDTO(Rol rol);
    Rol rolDTOToRol(RolDTO rolDTO);
}
