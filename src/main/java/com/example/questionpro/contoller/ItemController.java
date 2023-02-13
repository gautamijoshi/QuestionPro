package com.example.questionpro.contoller;

import com.example.questionpro.model.Item;
import com.example.questionpro.model.TYPE;
import com.example.questionpro.model.TopStoryHistory;
import com.example.questionpro.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ItemController {
    @Autowired
    ItemService itemservice;


    @GetMapping("/top-stories")
    public ResponseEntity topStoryItems() {

        try {
            List<Item> topStoryItems = itemservice.findTopStory(TYPE.STORY);
            return ResponseEntity.status(HttpStatus.OK).body(topStoryItems);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such stories found. Please try again");
        }

    }

    @GetMapping("/past-stories")
    public ResponseEntity pastStory() {

        try {
            List<TopStoryHistory> pastTopStory = itemservice.getPastStories();
            return ResponseEntity.status(HttpStatus.OK).body(pastTopStory);

        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such comments found. Please try again");
        }

    }

    @GetMapping("/comments")
    public ResponseEntity comments(@RequestParam String id) {

        try {
            List<Item> comments = itemservice.getComments(Integer.parseInt(id));

            return ResponseEntity.status(HttpStatus.OK).body(comments);
        } catch (NoSuchElementException e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such comments found. Please try again");
        }

    }


}
