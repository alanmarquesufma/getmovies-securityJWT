package com.mongodb.test.dto;

import com.mongodb.test.enums.RoleEnum;

public record UsuarioDto(
    String nome,
    String login,
    String senha,
    RoleEnum role
) {
    
}
