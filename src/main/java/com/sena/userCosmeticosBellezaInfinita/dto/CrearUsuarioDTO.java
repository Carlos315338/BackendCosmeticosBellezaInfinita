package com.sena.userCosmeticosBellezaInfinita.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CrearUsuarioDTO {
    private String userId;
    private String userName;
    private String rolId;
    private String email;
    private String phoneNumber;
}

