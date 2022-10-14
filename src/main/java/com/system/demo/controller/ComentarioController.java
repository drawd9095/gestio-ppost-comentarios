package com.system.demo.controller;

import com.system.demo.dto.ComentarioDTO;
import com.system.demo.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping("/publicaciones/{publicacionId}/comentarios")
    public List<ComentarioDTO> listarComentarioPorPublicacionID(
            @PathVariable(value = "publicacionId") Long publicacionId){
        return comentarioService.obtenerComentariosPorPublicacionId(publicacionId);
    }

    @GetMapping("/comentarios/{publicacionId}/{comentarioId}")
    public ResponseEntity<ComentarioDTO> obtnerComentarioPorId(
            @PathVariable(value = "publicacionId") Long publicacionId,
            @PathVariable(value = "comentarioId") Long comentarioId){
        return new ResponseEntity<>(comentarioService.obtenerComenatioById(
                publicacionId,comentarioId),HttpStatus.OK);
    }

    @PostMapping("/publicaciones/{publicacionId}/comentarios")
    public ResponseEntity<ComentarioDTO> guardarComentario(
            @PathVariable(value = "publicacionId") Long publicacionId,
            @Valid @RequestBody ComentarioDTO comentarioRequestDTO){

        return new ResponseEntity<>(comentarioService.crearComentario(
                publicacionId,comentarioRequestDTO), HttpStatus.OK);
    }

    @PutMapping("/comentarios/{publicacionId}/{comentarioId}")
    public ResponseEntity<ComentarioDTO> actulizarPublicacion(
            @PathVariable(value = "publicacionId") Long publicacionId,
            @PathVariable(value = "comentarioId") Long comentarioId,
            @Valid @RequestBody ComentarioDTO comentarioRequestDTO){

            return new ResponseEntity<>(comentarioService.actualizarcomentario(
                    publicacionId,comentarioId,comentarioRequestDTO),HttpStatus.OK);
    }

    @DeleteMapping("/comentarios/delete/{publicacionId}/{comentarioId}")
    public ResponseEntity<String> elminarComentario(
            @PathVariable(value = "publicacionId") Long publicacionId,
            @PathVariable(value = "comentarioId") Long comentarioId){

        comentarioService.eliminarComentario(publicacionId,comentarioId);
        return new ResponseEntity<>("Comentario eliminado con éxito",HttpStatus.OK);
    }
}
