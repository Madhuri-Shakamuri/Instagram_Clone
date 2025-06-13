package com.instagram.instagramclone.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class UserDto 
{
    private int id;
    private String userName;

    @JsonProperty(access =Access.WRITE_ONLY)
    private String password;

    public UserDto() {
    }


    public UserDto(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public UserDto(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "UserDto [id=" + id + ", userName=" + userName + ", password=" + password + "]";
    }
    
    
    
}
