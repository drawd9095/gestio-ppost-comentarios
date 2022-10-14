package com.system.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginDTO implements Serializable {
    private String usernameOrEmail;
    private String password;
}
