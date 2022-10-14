package com.system.demo.controller;

import com.system.demo.dto.PublicacionDTO;
import com.system.demo.dto.PublicacionCompleteDTO;
import com.system.demo.service.PublicacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.system.demo.utils.Constantes.*;


@RestController
@RequestMapping("/api/publicaciones")
public class PublicacionController {

    @Autowired
    private PublicacionService service;

    @GetMapping("/findall")
    public PublicacionCompleteDTO listarPublicacioes(
            @RequestParam(value = "PageNumber", defaultValue = PAGE_NUMBER_DEFAULT, required = false) int pageNumber,
            @RequestParam(value = "PageSize", defaultValue = PAGE_SIZE_DEFAULT, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ORDER_BY_DEFAULT, required = false) String orderBy,
            @RequestParam(value = "sortDir", defaultValue = ORDER_DIRECTION_BY_DEFAULT, required = false) String sortDir){
        return service.obtenerPublicaciones(pageNumber, pageSize, orderBy, sortDir);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<PublicacionDTO> obtnerPublicacionbyId (@PathVariable long id){
        return new ResponseEntity<>(service.obtenerPublicacionbyID(id),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<PublicacionDTO> guardarPublicacion(@Valid @RequestBody PublicacionDTO publicacion){
        return new ResponseEntity<>(service.crearPublicacion(publicacion), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<PublicacionDTO> actualizarPublicacion (@Valid @RequestBody PublicacionDTO publicacionDTO,
                                                                 @PathVariable long id){
        return new ResponseEntity<>(service.actualizarPublicacion(publicacionDTO, id),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> elminiarPublicacion (@PathVariable long id){
        service.eliminarPublicacion(id);
        return new ResponseEntity<>("Publicacion eliminada con exito",HttpStatus.OK);
    }
}

