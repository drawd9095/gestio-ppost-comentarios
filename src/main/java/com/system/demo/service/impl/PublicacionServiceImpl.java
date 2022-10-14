package com.system.demo.service.impl;

import com.system.demo.dto.PublicacionDTO;
import com.system.demo.dto.PublicacionCompleteDTO;
import com.system.demo.entity.Publicacion;
import com.system.demo.exceptions.ResourceNotfoundException;
import com.system.demo.repository.PublicacionRepository;
import com.system.demo.service.PublicacionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PublicacionRepository repository;

    @Override
    public PublicacionDTO crearPublicacion(PublicacionDTO publicacionDTO) {
        Publicacion nuevaPublicacion = repository.save(mappearEntidad(publicacionDTO));
        return mappearDTO(nuevaPublicacion);
    }

    @Override
    public PublicacionCompleteDTO obtenerPublicaciones(int pageNumber, int pagesize,
                                                       String orderBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ?Sort.by(orderBy).ascending()
                :Sort.by(orderBy).descending();

        Pageable pageable = PageRequest.of(pageNumber,pagesize, sort);
        Page<Publicacion> publicaciones = repository.findAll(pageable);

        List<Publicacion> listaPublicaciones = publicaciones.getContent();
        List<PublicacionDTO> contenido = listaPublicaciones
                .stream()
                .map(publicacion -> mappearDTO(publicacion))
                .collect(Collectors.toList());

        PublicacionCompleteDTO publicacionRespuestaDTO = new PublicacionCompleteDTO();
        publicacionRespuestaDTO.setContenido(contenido);
        publicacionRespuestaDTO.setPageNumber(publicaciones.getNumber());
        publicacionRespuestaDTO.setPageSize(publicaciones.getSize());
        publicacionRespuestaDTO.setTotalElements(publicaciones.getTotalElements());
        publicacionRespuestaDTO.setTotalPages(publicaciones.getTotalPages());
        publicacionRespuestaDTO.setUltima(publicaciones.isLast());

        return publicacionRespuestaDTO;
    }

    @Override
    public PublicacionDTO obtenerPublicacionbyID(long Id) {
        Publicacion publicacion = repository
                .findById(Id)
                .orElseThrow(() ->new ResourceNotfoundException("Publicacion", "Id",Id));

        return mappearDTO(publicacion);
    }

    @Override
    public PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id) {
        Publicacion publicacion = repository
                .findById(id)
                .orElseThrow(() ->new ResourceNotfoundException("Publicacion", "Id",id));

        publicacion.setContenido(publicacionDTO.getContenido());
        publicacion.setDescripcion(publicacionDTO.getDescripcion());
        publicacion.setTitulo(publicacionDTO.getTitulo());

        return mappearDTO(repository.save(publicacion));
    }

    @Override
    public void eliminarPublicacion(long id) {
        Publicacion publicacion = repository
                .findById(id)
                .orElseThrow(() ->new ResourceNotfoundException("Publicacion", "Id",id));

        repository.delete(publicacion);
    }


    //convierte de entidad a DTO
    private PublicacionDTO mappearDTO(Publicacion publicacion){
        return modelMapper.map(publicacion, PublicacionDTO.class);
    }

    private Publicacion mappearEntidad(PublicacionDTO publicacionDTO){
        return modelMapper.map(publicacionDTO,Publicacion.class);
    }
}
