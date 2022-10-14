package com.system.demo.Security;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponseDTO {

    private String tokenAcceso;
    private String tipoToken = "Bearer";

    public JWTAuthResponseDTO(String tokenAcceso) {
        this.tokenAcceso = tokenAcceso;
    }
}
