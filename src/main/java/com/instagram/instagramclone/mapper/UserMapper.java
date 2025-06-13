package com.instagram.instagramclone.mapper;

import com.instagram.instagramclone.dto.UserDto;
import com.instagram.instagramclone.model.User;

public class UserMapper 
{
    public static UserDto toUserDto(User user)
    {
     return new UserDto(user.getId(),user.getUserName());
    }

    public static User toUserEntity(UserDto userDto)
    {
        return new User(userDto.getId(),userDto.getUserName());
    }
    
}
