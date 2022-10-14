package com.system.demo.service;

import com.system.demo.dto.ComentarioDTO;

import java.util.List;

public interface ComentarioService {

    ComentarioDTO crearComentario (Long publicacionID, ComentarioDTO comentario);

    List<ComentarioDTO> obtenerComentariosPorPublicacionId (Long publicacionId);

    ComentarioDTO obtenerComenatioById (Long publicacionId, Long comentarioId);

    ComentarioDTO actualizarcomentario (Long publicacionId, Long comentarioId, ComentarioDTO comentarioDTO);

    void eliminarComentario (Long publicacionId, Long comentarioId);
}
