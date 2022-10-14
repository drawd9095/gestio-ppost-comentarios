package com.system.demo.service;

import com.system.demo.dto.PublicacionDTO;
import com.system.demo.dto.PublicacionCompleteDTO;

public interface PublicacionService {

    PublicacionDTO crearPublicacion(PublicacionDTO publicacion);

    PublicacionCompleteDTO obtenerPublicaciones(int pageNumber, int pagesize, String orderBy, String sortDir);

    PublicacionDTO obtenerPublicacionbyID(long Id);

    PublicacionDTO actualizarPublicacion(PublicacionDTO publicacionDTO, long id);

    void eliminarPublicacion(long id);
}
