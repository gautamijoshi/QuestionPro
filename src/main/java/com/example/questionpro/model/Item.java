package com.example.questionpro.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Item {
    @Id
    @GeneratedValue
    private Integer id;

    private boolean isDeleted;

    @Enumerated(EnumType.STRING)
    private TYPE type;

    @ManyToOne
    @JoinColumn(name = "by_user")
    private User byUser;

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private Date time;
    @Column(columnDefinition = "VARCHAR(255)")
    private String text;
    private boolean isDead;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Item parent;

    @OneToMany(mappedBy = "parent")
    private Set<Item> kids = new HashSet<Item>();
    @Column(columnDefinition = "integer default 0")
    private long poll;
    @Column(columnDefinition = "VARCHAR(255)")
    private String url;
    @Column(columnDefinition = "integer default 0")
    private long score;

    @Column(columnDefinition = "VARCHAR(255)")
    private String title;

    @ManyToMany
    @JoinTable(name = "item_parts", joinColumns = {@JoinColumn(name = "part_id")}, inverseJoinColumns = {@JoinColumn(name = "item_id")})
    private Set<Item> parts = new HashSet<>();

    private int descendants;

    public Item() {
    }

    public Item(Integer id, boolean isDeleted, TYPE type, User byUser, Date time, String text, boolean isDead, Item parent, long poll, Set<Item> kids, String url, long score, String title, Set<Item> parts, int descendants) {
        this.id = id;
        this.isDeleted = isDeleted;
        this.type = type;
        this.byUser = byUser;
        this.time = time;
        this.text = text;
        this.isDead = isDead;
        this.parent = parent;
        this.poll = poll;
        this.kids = kids;
        this.url = url;
        this.score = score;
        this.title = title;
        this.parts = parts;
        this.descendants = descendants;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public User getByUser() {
        return byUser;
    }

    public void setByUser(User byUser) {
        this.byUser = byUser;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public Item getParent() {
        return parent;
    }

    public void setParent(Item parent) {
        this.parent = parent;
    }

    public long getPoll() {
        return poll;
    }

    public void setPoll(long poll) {
        this.poll = poll;
    }

    public Set<Item> getKids() {
        return kids;
    }

    public void setKids(Set<Item> kids) {
        this.kids = kids;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Item> getParts() {
        return parts;
    }

    public void setParts(Set<Item> parts) {
        this.parts = parts;
    }

    public int getDescendants() {
        return descendants;
    }

    public void setDescendants(int descendants) {
        this.descendants = descendants;
    }


    public int getKidsCount() {

        return this.kids.size();
    }
}
