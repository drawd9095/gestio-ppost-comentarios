package com.system.demo.repository;

import com.system.demo.entity.Roll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Roll,Long> {

    Optional<Roll> findByNombre(String nombre);
}
