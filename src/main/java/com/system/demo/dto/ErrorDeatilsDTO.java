package com.system.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDeatilsDTO implements Serializable {

    private Date marcaDeTiempo;
    private String mensaje;
    private String detalle;
}
