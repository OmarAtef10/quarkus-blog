package com.example.resource;

import com.example.entity.BlogPost;
import com.example.entity.Comments;
import com.example.service.BlogPostService;
import com.example.service.CommentsService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/comments")
@Produces("application/json")
@Consumes({"application/json", "application/x-www-form-urlencoded", "multipart/form-data"})
public class CommentsResource {
    @Inject
    CommentsService commentsService;
    @Inject
    BlogPostService blogPostService;

    @GET
    @Path("/{blog_id}")
    public Response getPostComments(@PathParam("blog_id") Long blog_id) {
        BlogPost blog = blogPostService.getPostById(blog_id);
        if (blog == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Post with id " + blog_id + " was not found.").build();
        }
        List<Comments> comments = commentsService.getAllPostComments(blog_id);
        return Response.ok(comments).build();
    }

    @POST
    @Transactional
    @Path("/{blog_id}")
    public Response createComment(@PathParam("blog_id") Long blog_id, Comments comment) {
        try {
            BlogPost blog = blogPostService.getPostById(blog_id);
            if (blog == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Post with id " + blog_id + " was not found.").build();
            }
            comment.setBlogPost(blog);
            commentsService.createComment(comment);
            return Response.status(Response.Status.CREATED).entity(comment).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("An error occurred: " + e.getMessage()).build();
        }
    }




    @DELETE
    @Transactional
    @Path("/{blog_id}/{comment_id}")
    public Response deleteComment(@PathParam("blog_id") Long blog_id, @PathParam("comment_id") Long comment_id) {
        BlogPost blog = blogPostService.getPostById(blog_id);
        if (blog == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Post with id " + blog_id + " was not found.").build();
        }
        Comments comment = commentsService.getCommentById(blog_id, comment_id);
        if (comment == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Comment with id " + comment_id + " was not found.").build();
        }
        List<Comments> blogCommentsList = blog.getCommentsList();
        if (!blogCommentsList.contains(comment)) {
            return Response.status(Response.Status.NOT_FOUND).entity("Comment with id " + comment_id + " was not found in post with id " + blog_id + ".").build();
        }

        blogCommentsList.remove(comment);
        blog.setCommentsList(blogCommentsList);

        commentsService.deleteComment(comment_id);
        return Response.ok("Comment with id " + comment_id + " was successfully deleted.").build();
    }

}

