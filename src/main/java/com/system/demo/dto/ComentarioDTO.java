package com.system.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioDTO implements Serializable {

    private long id;

    @NotEmpty(message = "El título no debe ser vacío")
    @Size(min = 2, message = "El titulo debe tener al menos 2 caracteres")
    private String nombre;

    @NotEmpty(message = "El correo no debe ser vacío")
    @Email
    private String email;

    @NotEmpty(message = "El cuerpo no debe ser vacio o nulo")
    @Size(min = 10, message = "El cuerpo debe tener al menos 10 caracteres")
    private String cuerpo;

}
