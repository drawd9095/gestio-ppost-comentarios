package com.system.demo.Security;

import com.system.demo.entity.Roll;
import com.system.demo.entity.Usuario;
import com.system.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsernameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()-> new UsernameNotFoundException(
                        "Usuario no encontrado con ese username o email: "+usernameOrEmail));

        return  new User(usuario.getEmail(),usuario.getPassword(),mappearRolles(usuario.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mappearRolles(Set<Roll> roles){
        return roles.stream().map(roll -> new SimpleGrantedAuthority(
                roll.getNombre())).collect(Collectors.toList());
    }
}
