package com.example.questionpro.contoller;

import com.example.questionpro.model.Item;
import com.example.questionpro.model.type;
import com.example.questionpro.repository.IitemRepository;
import com.example.questionpro.service.Itemservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ItemController {
    @Autowired
    Itemservice itemservice;


    @GetMapping("/top-story")
    public ResponseEntity topStoryItems(){

        try {
            List<Item> topStoryItems = itemservice.findTopStory(type.STORY);
            return ResponseEntity.status(HttpStatus.OK).body(topStoryItems);
        } catch (NoSuchElementException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such stories found. Please try again");
        }

    }

    @GetMapping("/comments")
    public ResponseEntity comments(@RequestParam String id ) {

        try {
            List<Item> comments = itemservice.getComments(Integer.parseInt(id));

            return ResponseEntity.status(HttpStatus.OK).body(comments);
        } catch (NoSuchElementException e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No such comments found. Please try again");
        }

    }

}
