package com.rancho.estoque_api.repository;

import com.rancho.estoque_api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNomeGuerra(String NomeGuerra);
}
