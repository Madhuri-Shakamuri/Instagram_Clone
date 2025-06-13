package com.instagram.instagramclone.model;

import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class Post 
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Post() {
    }

    

    public Post(String content, LocalDateTime createdAt) {
        this.content = content;
        this.createdAt = createdAt;
    }



    public Post(String content, LocalDateTime createdAt, User user) {
        this.content = content;
        this.createdAt = createdAt;
        this.user = user;
    }


    public int getId() {
        return id;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "Post [id=" + id + ", content=" + content + ", createdAt=" + createdAt + ", user=" + user + "]";
    }

    

     
}

