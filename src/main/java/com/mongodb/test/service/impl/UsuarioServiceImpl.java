package com.mongodb.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mongodb.test.dto.UsuarioDto;
import com.mongodb.test.enums.RoleEnum;
import com.mongodb.test.model.Usuario;
import com.mongodb.test.repositories.UsuarioRepository;
import com.mongodb.test.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private RoleEnum role;

    @Override
    public UsuarioDto salvar(UsuarioDto usuarioDto) {
        
        Usuario usuarioJaExiste = usuarioRepository.findByLogin(usuarioDto.login());

        if (usuarioJaExiste != null) {
            throw new RuntimeException("Usuario ja existe!");
        }

        role = usuarioDto.role() != null ? usuarioDto.role() : role.USER;

        String passwordHash = passwordEncoder.encode(usuarioDto.senha());

        Usuario entity = new Usuario(usuarioDto.nome(), usuarioDto.login(), passwordHash, role);
        Usuario novoUsuario = usuarioRepository.save(entity);
        return new UsuarioDto(novoUsuario.getNome(), novoUsuario.getLogin(),novoUsuario.getSenha(), novoUsuario.getRole());
    }
    
}
