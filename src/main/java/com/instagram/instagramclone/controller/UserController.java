package com.instagram.instagramclone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagramclone.dto.UserDto;
import com.instagram.instagramclone.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController 
{
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public List<UserDto> getUsers()
    {
        return  userService.getUsers();
    }
    
    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto)
    {
        userService.createUser(userDto);
        return ResponseEntity.ok("User created!");
    }
    
}
