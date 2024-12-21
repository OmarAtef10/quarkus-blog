package com.example.service;

import com.example.entity.BlogPost;
import com.example.repository.BlogPostRepository;
import com.example.resource.BlogPostResource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

import java.util.List;

@ApplicationScoped
public class BlogPostService {

    @Inject
    BlogPostRepository blogPostRepository;

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.listAll();
    }

    public void createPost(BlogPost post) {
        blogPostRepository.persist(post);
    }

    public BlogPost getPostById(Long id) {
        return blogPostRepository.findById(id);
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }



}
