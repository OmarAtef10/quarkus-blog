package com.example.repository;

import com.example.entity.BlogPost;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BlogPostRepository  implements PanacheRepository<BlogPost> {

}
