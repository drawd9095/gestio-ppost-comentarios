package com.system.demo.Security;

import com.system.demo.exceptions.BlogappException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private int jwtExpirationInMs;

    public String generarToken(Authentication authentication){
        String username = authentication.getName();
        Date fechaExpiracion  = new Date(new Date().getTime() + jwtExpirationInMs);

        return  Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(fechaExpiracion)
                .signWith(SignatureAlgorithm.HS512,jwtSecret).compact();
    }

    public String obtenerUsernameJWT(String token){
        return Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validarToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex){
            throw new BlogappException(HttpStatus.BAD_REQUEST,"Firma JWT no válida");
        }catch (MalformedJwtException ex){
            throw new BlogappException(HttpStatus.BAD_REQUEST,"Token JWT no válido");
        }catch (ExpiredJwtException ex){
            throw new BlogappException(HttpStatus.BAD_REQUEST,"Token JWT caducado");
        }catch (UnsupportedJwtException ex){
            throw new BlogappException(HttpStatus.BAD_REQUEST,"Token JWT no compatible");
        }catch (IllegalArgumentException ex){
            throw new BlogappException(HttpStatus.BAD_REQUEST,"La cadena Claims está vacía");
        }
    }
}
