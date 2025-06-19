package com.instagram.instagramclone.response;

import com.instagram.instagramclone.dto.UserDto;

public class UserResponse 
{
    private String message;
    private UserDto user;
    private String token;
    
    
    public UserResponse(String message,UserDto user, String token) {
        this.message=message;
        this.user = user;
        this.token = token;
    }
    
    public UserDto getUser() {
        return user;
    }
    public void setUser(UserDto user) {
        this.user = user;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String toString() {
        return "UserResponse [user=" + user + ", token=" + token + "]";
    }

    

    
    
    
}
