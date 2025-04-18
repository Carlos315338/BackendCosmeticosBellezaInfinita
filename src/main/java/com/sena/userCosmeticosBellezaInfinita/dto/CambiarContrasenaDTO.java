package com.sena.userCosmeticosBellezaInfinita.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CambiarContrasenaDTO {
    private String idUser;
    private String contrasenaActual;
    private String contrasenaNueva;
    private String accessToken;
}
