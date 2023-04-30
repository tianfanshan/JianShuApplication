package com.stf.service;

import com.stf.entity.Article;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ArticleService {

    Optional<Article> findArticleById(Long id);

    List<Article> findAllArticle();

    Article createArticle(Article article);

    Article updateArticle(Article article);

    void removeArticleById(Long id);

    Article findByTitle(String title);
}
