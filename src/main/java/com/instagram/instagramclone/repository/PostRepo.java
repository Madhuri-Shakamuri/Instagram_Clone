package com.instagram.instagramclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instagram.instagramclone.model.Post;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer>{
    
}
