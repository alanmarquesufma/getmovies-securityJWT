package com.mongodb.test.model;




import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Document(collection="movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movies {
    
    @Id
    private String id;
    private String title;
    private Date released;
    private int runtime;
    private int num_mflix_comments;
    private String fullplot;
    private String rated;
    private String lastupdated;
    private int year;
    private String type;
    
}
