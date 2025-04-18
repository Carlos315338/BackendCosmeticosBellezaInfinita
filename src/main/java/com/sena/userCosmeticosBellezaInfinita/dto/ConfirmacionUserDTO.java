package com.sena.userCosmeticosBellezaInfinita.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConfirmacionUserDTO {
    private String username;
    private String tempPassword;
    private String newPassword;
}