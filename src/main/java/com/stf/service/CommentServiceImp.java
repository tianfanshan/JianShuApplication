package com.stf.service;

import com.stf.entity.Article;
import com.stf.entity.Comment;
import com.stf.entity.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImp implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    @Override
    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    @Transactional
    @Override
    public List<Comment> findAllCommentsByArticle(Article article) {
        return commentRepository.findCommentsByArticle(article);
    }

    @Transactional
    @Override
    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Transactional
    @Override
    public void removeCommentById(Long id) {
        commentRepository.deleteById(id);
    }
}
