package com.system.demo.service.impl;

import com.system.demo.dto.ComentarioDTO;
import com.system.demo.entity.Comentario;
import com.system.demo.entity.Publicacion;
import com.system.demo.exceptions.BlogappException;
import com.system.demo.exceptions.ResourceNotfoundException;
import com.system.demo.repository.ComentarioRepository;
import com.system.demo.repository.PublicacionRepository;
import com.system.demo.service.ComentarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @Autowired
    private PublicacionRepository publicacionRepository;


    @Override
    public ComentarioDTO crearComentario(Long publicacionID, ComentarioDTO comentarioDTO) {
        Comentario comentario = mappearEntidad(comentarioDTO);

        Publicacion publicacion = publicacionRepository
                .findById(publicacionID)
                .orElseThrow(() ->new ResourceNotfoundException("Publicacion", "Id",publicacionID));

        comentario.setPublicacion(publicacion);

        Comentario nuevoComentario = comentarioRepository.save(comentario);

        return mappearDTO(nuevoComentario);
    }

    @Override
    public List<ComentarioDTO> obtenerComentariosPorPublicacionId(Long publicacionId) {
        List<Comentario> comentarios = comentarioRepository.findByPublicacionId(publicacionId);
        return comentarios.stream().map(comentario -> mappearDTO(comentario)).collect(Collectors.toList());
    }

    @Override
    public ComentarioDTO obtenerComenatioById(Long publicacionId, Long comentarioId) {

        Publicacion publicacion = publicacionRepository
                .findById(publicacionId)
                .orElseThrow(() ->new ResourceNotfoundException("Publicacion", "Id",publicacionId));

        Comentario comentario = comentarioRepository
                .findById(comentarioId)
                .orElseThrow(()-> new ResourceNotfoundException("Comentario", "Id",comentarioId));

        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogappException(HttpStatus.BAD_REQUEST,"El comentario no pertenece a la publicacion");
        }

        return mappearDTO(comentario);
    }

    @Override
    public ComentarioDTO actualizarcomentario(Long publicacionId,Long comentarioId,ComentarioDTO comentarioDTO) {

        Publicacion publicacion = publicacionRepository
                .findById(publicacionId)
                .orElseThrow(() ->new ResourceNotfoundException("Publicacion", "Id",publicacionId));

        Comentario comentario = comentarioRepository
                .findById(comentarioId)
                .orElseThrow(()-> new ResourceNotfoundException("Comentario", "Id",comentarioId));

        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogappException(HttpStatus.BAD_REQUEST,"El comentario no pertenece a la publicacion");
        }

        comentario.setNombre(comentarioDTO.getNombre());
        comentario.setEmail(comentarioDTO.getEmail());
        comentario.setCuerpo(comentarioDTO.getCuerpo());

        Comentario comentarioActulizado = comentarioRepository.save(comentario);

        return mappearDTO(comentarioActulizado);
    }

    @Override
    public void eliminarComentario(Long publicacionId, Long comentarioId) {

        Publicacion publicacion = publicacionRepository
                .findById(publicacionId)
                .orElseThrow(() ->new ResourceNotfoundException("Publicacion", "Id",publicacionId));

        Comentario comentario = comentarioRepository
                .findById(comentarioId)
                .orElseThrow(()-> new ResourceNotfoundException("Comentario", "Id",comentarioId));

        if(!comentario.getPublicacion().getId().equals(publicacion.getId())){
            throw new BlogappException(HttpStatus.BAD_REQUEST,"El comentario no pertenece a la publicacion");
        }

        comentarioRepository.delete(comentario);
    }

    //mappear de entidad a DTO
    private ComentarioDTO mappearDTO(Comentario comentario) {
        return modelMapper.map(comentario,ComentarioDTO.class);
    }

    //mappear DTO a Entidad
    private Comentario mappearEntidad(ComentarioDTO comentarioDTO) {
        return modelMapper.map(comentarioDTO,Comentario.class);
    }

}
