package com.example.service;

import com.example.entity.Comments;
import com.example.repository.BlogPostRepository;
import com.example.repository.CommentsRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CommentsService {
    @Inject
    CommentsRepository commentsRepository;

    public List<Comments> getAllPostComments(Long blogId) {
        return commentsRepository.findByBlog(blogId);
    }

    public void createComment(Comments comment) {
        commentsRepository.persist(comment);
    }

    public Comments getCommentById(Long blogId, Long commentId) {
        return commentsRepository.findById(commentId);
    }


    public void deleteComment (Long commentId) {
        commentsRepository.deleteById(commentId);
    }

}
