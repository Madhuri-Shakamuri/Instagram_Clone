package com.instagram.instagramclone.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;

import com.instagram.instagramclone.dto.PostDto;
import com.instagram.instagramclone.model.User;
import com.instagram.instagramclone.model.UserPrincipal;
import com.instagram.instagramclone.service.PostService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
public class PostControllerTest {

    @Mock
    private PostService postService;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private PostController postController;

    @Test
    void testCreatePost_success() {
        
        User mockUser = new User(101, "madhuri", "pass123");
        UserPrincipal userPrincipal = new UserPrincipal(mockUser);

        PostDto postDto = new PostDto(0, "My first post!", LocalDateTime.now(), 0, "madhuri");

       
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getPrincipal()).thenReturn(userPrincipal);

        when(postService.createPost(postDto, 101)).thenReturn(postDto);

        ResponseEntity<String> response = postController.createPost(postDto);

       
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Post Created successfully!", response.getBody());

        verify(postService, times(1)).createPost(postDto, 101);
    }
}
