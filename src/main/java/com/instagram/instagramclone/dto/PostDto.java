package com.instagram.instagramclone.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class PostDto 
{

    private int id;
    private String content;
    private LocalDateTime createdAt;
    @JsonProperty(access = Access.READ_ONLY)
    private int userId;
    private String userName;

    public PostDto() {
    }

    public PostDto(int id, String content, LocalDateTime createdAt, int userId, String userName) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.userId = userId;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "PostDto [id=" + id + ", content=" + content + ", createdAt=" + createdAt + ", userId=" + userId
                + ", userName=" + userName + "]";
    }

    
    
    
}
