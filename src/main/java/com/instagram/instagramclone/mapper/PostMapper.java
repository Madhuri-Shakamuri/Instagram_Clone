package com.instagram.instagramclone.mapper;

import com.instagram.instagramclone.dto.PostDto;
import com.instagram.instagramclone.model.Post;
import com.instagram.instagramclone.model.User;

public class PostMapper 
{
    public static PostDto toPostDto(Post post)
    {
     return new PostDto(
        post.getId(),
        post.getContent(),
        post.getCreatedAt(),
        post.getUser().getId(),
        post.getUser().getUserName());
    }

    public static Post toPostEntity(PostDto postDto,User user)
    {
        return new Post(
        postDto.getContent(),
        postDto.getCreatedAt(),
        user);
    }
    
}
