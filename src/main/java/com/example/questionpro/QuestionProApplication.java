package com.example.questionpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QuestionProApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuestionProApplication.class, args);
    }

}
