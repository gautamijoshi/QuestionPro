package com.example.questionpro.service;

import com.example.questionpro.model.Item;
import com.example.questionpro.model.type;
import com.example.questionpro.repository.IitemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Itemservice {
    @Autowired
    IitemRepository repo;

    @Autowired
    CacheManager cacheManager;

    @Cacheable(key = "'topStory'", value = "topStoryCache")
    public List<Item> findTopStory(type story) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, -15);
        Date fifteenMinutesAgo = calendar.getTime();

        List<Item> topStory= repo.findTopStory(fifteenMinutesAgo,type.STORY);
        return topStory;
    }
    @Cacheable(key= "'comments'", value = "commentsCache")
    public List<Item> getComments(int id) {

        Item story= repo.getStory(id, type.STORY);
        List<Item> comments = repo.getCommentsForStory(type.COMMENT,story);
        Collections.sort(comments, Comparator.comparingInt(Item::getKidsCount));
        if(!comments.isEmpty())
            comments.subList(0,9).clear();
        return comments;
    }


}
