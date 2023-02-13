package com.example.questionpro.model;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date createdDate;
    private long Karma;
    @Column(columnDefinition = "VARCHAR(255)")
    private String about;

    @OneToMany(mappedBy = "byUser")
    private List<Item> submitted =new ArrayList<>();

    public User() {}


    public User(Integer id, Date createdDate, long karma, String about, List<Item> submitted) {
        this.id = id;
        this.createdDate = createdDate;
        Karma = karma;
        this.about = about;
        this.submitted = submitted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public long getKarma() {
        return Karma;
    }

    public void setKarma(long karma) {
        Karma = karma;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<Item> getSubmitted() {
        return submitted;
    }

    public void setSubmitted(List<Item> submitted) {
        this.submitted = submitted;
    }


}

