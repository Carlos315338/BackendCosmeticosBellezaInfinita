package com.sena.userCosmeticosBellezaInfinita.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RolDTO {

    private String idRol;
    private String nombreRol;
    private List<ModuloDTO> modulos;

}

