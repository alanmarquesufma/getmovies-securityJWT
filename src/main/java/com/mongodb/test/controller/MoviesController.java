package com.mongodb.test.controller;

import java.util.Date;



import com.mongodb.test.model.Movies;
import com.mongodb.test.service.MoviesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MoviesController {

    @Autowired
    MoviesService moviesService;

    @GetMapping("/between")
    public ResponseEntity<Page<Movies>> getIntervalo(
        @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
        @RequestParam("fim")  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date  end) {

        return ResponseEntity.ok(moviesService.getReleasedBetween(start, end));
    }
}
