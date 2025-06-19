package com.instagram.instagramclone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.instagram.instagramclone.dto.PostDto;
import com.instagram.instagramclone.model.UserPrincipal;
import com.instagram.instagramclone.service.PostService;
@RestController
public class PostController 
{
    
     @Autowired
    private PostService postService;


    @PostMapping("/post")
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto) 
    {
     
     Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        int userId = principal.getUser().getId(); 
        postService.createPost(postDto, userId);
     return ResponseEntity.ok("Post Created successfully!");
    
    }
    

    @GetMapping("/posts")
    public List<PostDto> getPosts()
    {
       return  postService.getPosts();
    }
}
