package com.system.demo.controller;

import com.system.demo.Security.JWTAuthResponseDTO;
import com.system.demo.Security.JwtTokenProvider;
import com.system.demo.dto.LoginDTO;
import com.system.demo.dto.RegistroDTO;
import com.system.demo.entity.Roll;
import com.system.demo.entity.Usuario;
import com.system.demo.repository.RolRepository;
import com.system.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginDTO.getUsernameOrEmail(),loginDTO.getPassword()));

        //obtener el token
        String token = jwtTokenProvider.generarToken(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok(new JWTAuthResponseDTO(token));

    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroDTO registroDTO){
        if(usuarioRepository.existsByUsername(registroDTO.getUsername())){
            return new ResponseEntity<>("Nombre de Usuario ya existe", HttpStatus.OK);
        }

        if(usuarioRepository.existsByUsername(registroDTO.getEmail())){
            return new ResponseEntity<>("Email de Usuario ya existe", HttpStatus.OK);
        }

        usuarioRepository.save(mappearUser(registroDTO));

        return new ResponseEntity<>("Usuario registrado con éxito", HttpStatus.OK);
    }

    private Usuario mappearUser(RegistroDTO registroDTO){
        Usuario usuario =  new Usuario();
        usuario.setNombre(registroDTO.getNombre());
        usuario.setUsername(registroDTO.getUsername());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));

        Roll roles = rolRepository.findByNombre("ROLE_ADMIN").get();
        usuario.setRoles(Collections.singleton(roles));

        return usuario;
    }
}
