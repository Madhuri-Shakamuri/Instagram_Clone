package com.instagram.instagramclone.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.instagramclone.Exception.UserNotFoundException;
import com.instagram.instagramclone.dto.PostDto;
import com.instagram.instagramclone.mapper.PostMapper;
import com.instagram.instagramclone.model.Post;
import com.instagram.instagramclone.model.User;
import com.instagram.instagramclone.repository.PostRepo;
import com.instagram.instagramclone.repository.UserRepo;

@Service
public class PostService 
{


    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    public void createPost(PostDto postDto)
    {
       User user = userRepo.findById(postDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User with ID " + postDto.getUserId() + " not found"));

        Post post = PostMapper.toPostEntity(postDto, user);

        post.setCreatedAt(LocalDateTime.now());
        Post savedPost = postRepo.save(post);
        PostMapper.toPostDto(savedPost);

    }
    
    public List<PostDto> getPosts()
    {
        return postRepo.findAll().stream().map(PostMapper::toPostDto).collect(Collectors.toList());
    }
    
}
