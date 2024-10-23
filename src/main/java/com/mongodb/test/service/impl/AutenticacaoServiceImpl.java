package com.mongodb.test.service.impl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mongodb.test.dto.AuthDto;
import com.mongodb.test.model.Usuario;
import com.mongodb.test.repositories.UsuarioRepository;
import com.mongodb.test.service.AutenticacaoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoServiceImpl implements AutenticacaoService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(login);
    }

    @Override
    public String obterToken(AuthDto authDto) {
        Usuario usuario = usuarioRepository.findByLogin(authDto.login());
        return gerarTokenJwt(usuario);
    }

    public String gerarTokenJwt(Usuario usuario){

        try {
            
            Algorithm algorithm =  Algorithm.HMAC256("movies@api");

            return JWT.create().withIssuer("auth-api")
                               .withSubject(usuario.getLogin())
                               .withExpiresAt(gerarDataExpiracao())
                               .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Erro ao tentar gerar o token! " + e.getMessage());
        }
    }

    public String validarTokenJwt(String token){
        try {
            
            Algorithm algorithm =  Algorithm.HMAC256("movies@api");

            return JWT.require(algorithm).withIssuer("auth-api")
                                         .build()
                                         .verify(token)
                                         .getSubject();

        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private Instant gerarDataExpiracao() {
       return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-03:00"));
    }
    
}
