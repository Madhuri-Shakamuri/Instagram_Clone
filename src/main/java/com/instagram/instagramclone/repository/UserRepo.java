package com.instagram.instagramclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.instagram.instagramclone.model.User;


@Repository
public interface UserRepo extends JpaRepository<User,Integer>{
    
}
