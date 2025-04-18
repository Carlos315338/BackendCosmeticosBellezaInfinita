package com.sena.userCosmeticosBellezaInfinita.repository;

import com.sena.userCosmeticosBellezaInfinita.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
}

