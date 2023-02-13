package com.example.questionpro.service;

import com.example.questionpro.model.Item;
import com.example.questionpro.model.TYPE;
import com.example.questionpro.model.TopStoryHistory;
import com.example.questionpro.repository.ItemRepository;
import com.example.questionpro.repository.TopStoryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    TopStoryHistoryRepository topStoryHistoryRepository;

    @Autowired
    CacheManager cacheManager;

    @Cacheable(key = "'topStory'", value = "topStoryCache")
    public List<Item> findTopStory(TYPE story) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE, -15);
        Date fifteenMinutesAgo = calendar.getTime();

        List<Item> topStory = itemRepository.findTopStory(fifteenMinutesAgo, TYPE.STORY);
        if (!topStory.isEmpty())
            saveTopStoryHistory(topStory);

        return topStory;
    }


    @Cacheable(key = "'comments'", value = "commentsCache")
    public List<Item> getComments(int id) {

        Item story = itemRepository.getStory(id, TYPE.STORY);
        List<Item> comments = itemRepository.getCommentsForStory(TYPE.COMMENT, story);
        Collections.sort(comments, Comparator.comparingInt(Item::getKidsCount));
        if (!comments.isEmpty())
            comments.subList(0, 9).clear();
        return comments;
    }

    private void saveTopStoryHistory(List<Item> topStory) {

        TopStoryHistory topStoryHistory = new TopStoryHistory(topStory, new Date());
        topStoryHistoryRepository.save(topStoryHistory);
    }

    @Cacheable(key = "'pastStory'", value = "pastStorycache")
    public List<TopStoryHistory> getPastStories() {
        return topStoryHistoryRepository.findByOrderByTimestampDesc();

    }

}
