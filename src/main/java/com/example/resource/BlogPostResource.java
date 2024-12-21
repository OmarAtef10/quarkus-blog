package com.example.resource;

import com.example.entity.BlogPost;
import com.example.repository.BlogPostRepository;
import com.example.service.BlogPostService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/posts")
@Produces("application/json")
@Consumes({"application/json", "application/x-www-form-urlencoded", "multipart/form-data"})
public class BlogPostResource {


    @Inject
    BlogPostService blogPostService;

    @GET
    public List<BlogPost> getAllPosts() {
        return blogPostService.getAllPosts();
    }


    @POST
    @Transactional
    public Response createPost(BlogPost post) {
        blogPostService.createPost(post);
        return Response.status(Response.Status.CREATED).entity(post).build();
    }


    @GET
    @Path("/{id}")
    public Response getPost(@PathParam("id") Long id) {
        BlogPost blog = blogPostService.getPostById(id);
        if (blog == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Post with id " + id + " was not found.").build();
        }
        return Response.ok(blog).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletePost(@PathParam("id") Long id) {
        BlogPost blog = blogPostService.getPostById(id);
        if (blog == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Post with id " + id + " was not found.").build();
        }
        blogPostService.deletePost(id);
        return Response.ok("Post with id " + id + " was successfully deleted.").build();
    }


}
