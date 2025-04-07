package com.sena.backendCosmeticosBellezaInfinita.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "roles")
public class Rol {

    @Id
    @Column(name = "id_rol", length = 36, nullable = false)
    private String idRol = UUID.randomUUID().toString();

    @Column(name = "nombre_rol", length = 50, nullable = false, unique = true)
    private String nombreRol;

}

