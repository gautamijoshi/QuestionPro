package com.example.questionpro.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
public class TopStoryHistory {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToMany
    @JoinTable(name = "top_story_history_item", joinColumns = {@JoinColumn(name = "item_id")}, inverseJoinColumns = {@JoinColumn(name = "top_story_history_id")})
    private List<Item> topStory;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date timestamp;

    public TopStoryHistory() {
    }

    public TopStoryHistory(List<Item> topStory, Date timestamp) {
        this.topStory = topStory;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Item> getTopStory() {
        return topStory;
    }

    public void setTopStory(List<Item> topStory) {
        this.topStory = topStory;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
