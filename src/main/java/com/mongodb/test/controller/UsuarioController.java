package com.mongodb.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mongodb.test.dto.UsuarioDto;
import com.mongodb.test.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/admin")
    private String getAdmin(){
        return "Permissao de administrador!";
    }

    @GetMapping("/user")
    private String getUser(){
        return "Permissao de usuario!";
    }


    @PostMapping
    private UsuarioDto salvar(@RequestBody UsuarioDto usuarioDto){
        
        return usuarioService.salvar(usuarioDto);
    }
}