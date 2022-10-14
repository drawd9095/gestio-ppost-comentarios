package com.system.demo.repository;

import com.system.demo.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario,Long> {

    List<Comentario> findByPublicacionId(long publicacionId);

}
