package com.system.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RegistroDTO implements Serializable {

    private String nombre;
    private String username;
    private String email;
    private String password;
}
