package com.sena.backendCosmeticosBellezaInfinita.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "rol_modulo",
            joinColumns = @JoinColumn(name = "id_rol"),
            inverseJoinColumns = @JoinColumn(name = "id_modulo")
    )
    private List<Modulo> modulos;


}

