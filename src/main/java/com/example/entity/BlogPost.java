package com.example.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class BlogPost extends PanacheEntity {
    @NotBlank
    public String title;

    @NotBlank
    public String content;



}
