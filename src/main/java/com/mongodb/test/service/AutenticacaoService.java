package com.mongodb.test.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.mongodb.test.dto.AuthDto;

public interface AutenticacaoService extends UserDetailsService {
    
    public String obterToken(AuthDto authDto);

    public String validarTokenJwt(String token);
}
