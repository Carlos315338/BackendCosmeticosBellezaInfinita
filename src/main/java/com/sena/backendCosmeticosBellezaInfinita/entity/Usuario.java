package com.sena.backendCosmeticosBellezaInfinita.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
@Table(name = "usuarios")
public class Usuario {

    @Id
    @Column(name = "id_usuario", length = 36, nullable = false)
    private String idUsuario;

    @Column(name = "nombre_usuario", length = 50, nullable = false, unique = true)
    private String nombreUsuario;

    @Column(name = "contrasenha", length = 255, nullable = false)
    private String contrasenha;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    //private Rol rol;


    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }
}
