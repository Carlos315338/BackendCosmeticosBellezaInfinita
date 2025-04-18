package com.sena.userCosmeticosBellezaInfinita.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "id_usuario", length = 36, nullable = false)
    private String idUsuario;

    @Column(name = "nombre_usuario", length = 50, nullable = false, unique = true)
    private String nombreUsuario;

    @Column(name = "contrasenha", length = 255, nullable = false)
    private String contrasenha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private Rol rol;

}