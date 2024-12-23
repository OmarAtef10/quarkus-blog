package com.example.repository;

import com.example.entity.Comments;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CommentsRepository implements PanacheRepository<Comments> {

    public List<Comments> findByBlog(Long blogId) {
        return find("blogPost.id", blogId).list();
    }
}
