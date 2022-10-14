package com.system.demo.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BlogappException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpStatus httpStatus;
    private String mensaje;

    public BlogappException(HttpStatus httpStatus, String mensaje) {
        super();
        this.httpStatus = httpStatus;
        this.mensaje = mensaje;
    }
    public BlogappException(HttpStatus httpStatus, String mensaje, String mensaje1) {
        super();
        this.httpStatus = httpStatus;
        this.mensaje = mensaje;
        this.mensaje = mensaje1;
    }
}
