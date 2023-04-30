package com.stf.controller;

import com.stf.entity.Article;
import com.stf.entity.Comment;
import com.stf.service.ArticleService;
import com.stf.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/comments/id/{id}")
    public ResponseEntity<?> getCommentById(@PathVariable Long id){
        Optional<Comment> comment = commentService.getCommentById(id);
        if (comment.isEmpty()){
            return new ResponseEntity<>("Comment with id " + id + " not found",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(comment.get(),HttpStatus.OK);
    }

    @GetMapping("/comments/article/title/{title}")
    public ResponseEntity<?> getCommentsByArticle(@PathVariable String title){
        Article article = articleService.findByTitle(title);
        if (article == null){
            String response = "Article with title " + title + " not found";
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        }
        List<Comment> comments = commentService.findAllCommentsByArticle(article);
        System.out.println(comments.size());
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PutMapping("/comments/id/{id}")
    public ResponseEntity<?> updateCommentById(@PathVariable Long id,Comment comment){
        Optional<Comment> comment1 = commentService.getCommentById(id);
        if (comment1.isEmpty()){
            return new ResponseEntity<>("Comment with id " + id + " not found",HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(comment,comment1.get());
        Comment comment2 = commentService.updateComment(comment1.get());
        return new ResponseEntity<>(comment2,HttpStatus.OK);
    }

    @PostMapping("/comments/articleId/{articleId}")
    public ResponseEntity<?> createComment(@PathVariable Long articleId,Comment comment){
        Optional<Article> article = articleService.findArticleById(articleId);
        if (article.isEmpty()){
            return new ResponseEntity<>("This article with id " + articleId + " dose not exist",HttpStatus.BAD_REQUEST);
        }
        comment.setArticle(article.get());
        Comment comment1 = commentService.createComment(comment);
        return new ResponseEntity<>(comment1,HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/id/{id}")
    public ResponseEntity<?> removeCommentById(@PathVariable Long id){
        Optional<Comment> comment = commentService.getCommentById(id);
        if (comment.isEmpty()){
            return new ResponseEntity<>("The comment you want to remove dose not exist",HttpStatus.BAD_REQUEST);
        }
        commentService.removeCommentById(id);
        return new ResponseEntity<>("Comment with id " + id + " removed", HttpStatus.BAD_REQUEST);
    }

}
