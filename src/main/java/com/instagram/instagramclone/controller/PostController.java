package com.instagram.instagramclone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.instagram.instagramclone.dto.PostDto;
import com.instagram.instagramclone.service.PostService;

@RestController
@RequestMapping("/api")
public class PostController 
{
    
     @Autowired
    private PostService postService;


    @PostMapping("/post")
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto)
    {
     postService.createPost(postDto);
     return ResponseEntity.ok("Post Created successfully!");
    }

    @GetMapping("/posts")
    public List<PostDto> getPosts()
    {
       return  postService.getPosts();
    }
}
