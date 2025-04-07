package com.sena.backendCosmeticosBellezaInfinita.repository;

import com.sena.backendCosmeticosBellezaInfinita.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}

