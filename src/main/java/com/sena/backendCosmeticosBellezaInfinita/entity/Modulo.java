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
@Table(name = "modulos")
public class Modulo {

    @Id
    @Column(name = "id", length = 36, nullable = false)
    private String id = UUID.randomUUID().toString();

    @Column(name = "nombre", length = 100, nullable = false, unique = true)
    private String nombre;

}

