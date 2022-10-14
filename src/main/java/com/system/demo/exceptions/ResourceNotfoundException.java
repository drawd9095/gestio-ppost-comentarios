package com.system.demo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotfoundException extends RuntimeException{

    private String nombreRecurso;
    private String nombreCampo;
    private Long valorID;

    public ResourceNotfoundException(String nombreRecurso, String nombreCampo, Long valorID) {
        super(String.format("%s no encontrado con : %s : %s", nombreRecurso,nombreCampo,valorID));
        this.nombreRecurso = nombreRecurso;
        this.nombreCampo = nombreCampo;
        this.valorID = valorID;
    }

}
