package com.stf.service;

import com.stf.entity.Topic;

import java.util.Optional;

public interface TopicService {

    Topic createTopic(Topic topic);

    Optional<Topic> findTopicById(Long id);

    Topic updateTopic(Topic topic);

    Topic includeArticle(Long topicId, Long articleId);

    Topic unIncludeArticle(Long topicId, Long articleId);

    void removeTopic(Long id);
}
