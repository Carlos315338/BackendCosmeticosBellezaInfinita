package com.sena.backendCosmeticosBellezaInfinita.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CambiarClaveAdminDTO {
    private String idUser;
    private String contrasenaTemporal;
}
