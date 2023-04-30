package com.stf.service;

import com.stf.entity.Article;
import com.stf.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Optional<Comment> getCommentById(Long id);

    List<Comment> findAllCommentsByArticle(Article article);

    Comment updateComment(Comment comment);

    Comment createComment(Comment comment);

    void removeCommentById(Long id);
}
