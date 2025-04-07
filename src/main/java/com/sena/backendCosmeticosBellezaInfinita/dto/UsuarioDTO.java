package com.sena.backendCosmeticosBellezaInfinita.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioDTO {

    private String idUsuario;
    private String nombreUsuario;
    private RolDTO rol;
}
