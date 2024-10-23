package com.mongodb.test.service;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mongodb.test.repositories.MoviesRepository;

import com.mongodb.test.model.Movies;

@Service
public class MoviesService {

    @Autowired
    MoviesRepository moviesRepository;

    int pagina = 0, size= 1000;

    public Page<Movies> getReleasedBetween(Date start, Date end){
        try {
            Pageable pageable = PageRequest.of(pagina, size);
            

            // Busca no repositório entre as datas especificadas
            return moviesRepository.findByReleasedBetween(start, end, pageable);

        
        } catch (Exception e) {
            // Captura outras exceções não previstas
            System.err.println("Erro inesperado: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar filmes. Tente novamente mais tarde.");
        }
        
    }
}
