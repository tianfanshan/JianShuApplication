package com.stf.controller;

import com.stf.entity.Article;
import com.stf.entity.Topic;
import com.stf.service.ArticleService;
import com.stf.service.TopicService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1")
public class TopicController {

    @Autowired
    private TopicService topicService;

    @Autowired
    ArticleService articleService;

    @PostMapping("/topics")
    public ResponseEntity<?> createTopic(Topic topic){
        Topic topic1 = topicService.createTopic(topic);
        if (topic1 != null ){
            return new ResponseEntity<>(topic1,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Can not create topic, please check the requirements",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/topics/id/{id}")
    public ResponseEntity<?> getTopicById(@PathVariable Long id){
        Optional<Topic> topic = topicService.findTopicById(id);
        if (topic.isEmpty()){
            return new ResponseEntity<>("Topic with id " + id + " dose not exist", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(topic.get(),HttpStatus.OK);
    }

    @PutMapping("/topics/id/{id}")
    public ResponseEntity<?> updateTopic(@PathVariable Long id,Topic topic){
        Optional<Topic> topicOptional = topicService.findTopicById(id);
        if (topicOptional.isEmpty()){
            return new ResponseEntity<>("Topic with id " + id + " does not exist", HttpStatus.BAD_REQUEST);
        }
        BeanUtils.copyProperties(topic,topicOptional.get());
        Topic topic1 = topicService.updateTopic(topicOptional.get());
        return new ResponseEntity<>(topic1,HttpStatus.OK);
    }

    @PutMapping("/topics/includeArticle/topicId/{topicId}/articleId/{articleId}")
    public ResponseEntity<?> includeArticle(@PathVariable Long topicId,
                                            @PathVariable Long articleId){
        Optional<Topic> topic = topicService.findTopicById(topicId);
        Optional<Article> article = articleService.findArticleById(articleId);
        if (topic.isEmpty() || article.isEmpty()){
            return new ResponseEntity<>("Topic id or article id does not exist",HttpStatus.BAD_REQUEST);
        }
        if (topic.get().getArticles().contains(article.get())){
            return new ResponseEntity<>("This article is already exist in the topic",HttpStatus.BAD_REQUEST);
        }
        Topic topic1 = topicService.includeArticle(topicId,articleId);
        return new ResponseEntity<>(topic1,HttpStatus.OK);
    }

    @PutMapping("/topics/unIncludeArticle/topicId/{topicId}/articleId/{articleId}")
    public ResponseEntity<?> unIncludeArticle(@PathVariable Long topicId,
                                            @PathVariable Long articleId){
        Optional<Topic> topic = topicService.findTopicById(topicId);
        Optional<Article> article = articleService.findArticleById(articleId);
        if (topic.isEmpty() || article.isEmpty()){
            return new ResponseEntity<>("Topic id or article id does not exist",HttpStatus.BAD_REQUEST);
        }
        if (!topic.get().getArticles().contains(article.get())){
            return new ResponseEntity<>("This article dose not exist in this topic",HttpStatus.BAD_REQUEST);
        }
        Topic topic1 = topicService.unIncludeArticle(topicId,articleId);
        return new ResponseEntity<>(topic1,HttpStatus.OK);
    }

    @DeleteMapping("/topics/id/{id}")
    public ResponseEntity<?> removeTopicById(@PathVariable Long id){
        Optional<Topic> topic = topicService.findTopicById(id);
        if (topic.isEmpty()){
            return new ResponseEntity<>("Topic with id " + id +" does not exist",HttpStatus.BAD_REQUEST);
        }
        topicService.removeTopic(id);
        return new ResponseEntity<>("Topic with id " + id + " removed",HttpStatus.OK);
    }




}
