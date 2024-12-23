package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;

@Entity(name = "post-comments")
public class Comments extends PanacheEntity {
    @ManyToOne
    @JoinColumn(name = "blog_id")
    @JsonIgnore
    private BlogPost blogPost;

    @JsonProperty("blog_id")
    public Long getBlogId() {
        return blogPost != null ? blogPost.id : null;
    }


    @NotBlank
    private String content;


    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
