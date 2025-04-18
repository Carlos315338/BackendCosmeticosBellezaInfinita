package com.sena.userCosmeticosBellezaInfinita.mapper;

import com.sena.userCosmeticosBellezaInfinita.dto.ModuloDTO;
import com.sena.userCosmeticosBellezaInfinita.entity.Modulo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ModuloMapper {
    ModuloDTO moduloToModuloDTO(Modulo modulo);
    Modulo moduloDTOToModulo(ModuloDTO moduloDTO);
}
