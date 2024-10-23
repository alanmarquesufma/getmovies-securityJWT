package com.mongodb.test.repositories;


import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mongodb.test.model.Movies;

@Repository
public interface MoviesRepository extends MongoRepository<Movies, String>{
    
   
    Page<Movies> findByReleasedBetween(Date inicio, Date fim, Pageable pageable);

    
}
