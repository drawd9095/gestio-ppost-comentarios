package com.system.demo.repository;

import com.system.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByUsernameOrEmail(String userName, String email);

    Optional<Usuario> findByUsername(String userName);

    Boolean existsByUsername(String userName);

    Boolean existsByEmail(String email);
}
