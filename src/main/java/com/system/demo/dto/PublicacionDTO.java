package com.system.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionDTO implements Serializable {


    private Long id;

    @NotEmpty(message = "El título no debe ser vacío")
    @Size(min = 2, message = "El título de la pulicacion debe tener al menos 2 caracteres")
    private String titulo;

    @NotEmpty(message = "La descripción no debe ser vacío")
    @Size(min = 10, message = "La descripción de la pulicacion debe tener al menos 10 caracteres")
    private String descripcion;

    @NotEmpty(message = "El contenido no debe ser vacío")
    private String contenido;
    private Set<ComentarioDTO> comentarios;

}
