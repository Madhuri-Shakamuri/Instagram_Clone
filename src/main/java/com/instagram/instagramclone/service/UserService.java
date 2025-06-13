package com.instagram.instagramclone.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instagram.instagramclone.dto.UserDto;
import com.instagram.instagramclone.mapper.UserMapper;
import com.instagram.instagramclone.repository.UserRepo;

@Service
public class UserService {
    
    @Autowired
    private UserRepo userRepo;
    
    public void createUser(UserDto userDto)
    {
        userRepo.save(UserMapper.toUserEntity(userDto));
    }

    public List<UserDto> getUsers()
    {
        return  userRepo.findAll().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }
}
