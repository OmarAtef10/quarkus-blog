package com.example.resource;

import com.example.entity.BlogPost;
import com.example.repository.BlogPostRepository;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class BlogPostResourceTest {
    @InjectMock BlogPostRepository blogPostRepository;
    @Inject BlogPostResource blogPostResource;

    @Test
    void getAllPosts() {
        List<BlogPost> blogPosts = new ArrayList<>();
        BlogPost blogPost = new BlogPost();
        blogPost.title = "Test Title";
        blogPost.content = "Test Content";
        blogPosts.add(blogPost);
        Mockito.when(blogPostRepository.listAll()).thenReturn(blogPosts);
        List<BlogPost> result = blogPostResource.getAllPosts();
        assertEquals(blogPosts, result);
    }

    @Test
    void createPost() {
        BlogPost blogPost = new BlogPost();
        blogPost.title = "Test Title";
        blogPost.content = "Test Content";
        Response response = blogPostResource.createPost(blogPost);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(blogPost, response.getEntity());
        Mockito.verify(blogPostRepository).persist(blogPost);
    }

    @Test
    void getPost() {
        BlogPost blogPost = new BlogPost();
        blogPost.title = "Test Title";
        blogPost.content = "Test Content";
        Mockito.when(blogPostRepository.findById(1L)).thenReturn(blogPost);
        assertEquals(blogPost, blogPostResource.getPost(1L).getEntity());
        Mockito.verify(blogPostRepository).findById(1L);
        System.out.println(blogPostRepository.findById(1L));

    }

@Test
void deletePost() {
    BlogPost blogPost = new BlogPost();
    blogPost.title = "Test Title";
    blogPost.content = "Test Content";
    Mockito.when(blogPostRepository.findById(1L)).thenReturn(blogPost);
    Response response = blogPostResource.deletePost(1L);
    assertEquals("Post with id 1 was successfully deleted.", response.getEntity());
    Mockito.verify(blogPostRepository).deleteById(1L);
}
}