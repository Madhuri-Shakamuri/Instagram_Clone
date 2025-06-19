package com.instagram.instagramclone.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.instagram.instagramclone.dto.UserDto;
import com.instagram.instagramclone.mapper.UserMapper;
import com.instagram.instagramclone.model.User;
import com.instagram.instagramclone.repository.UserRepo;

@Service
public class UserService {
    
   @Autowired
   private UserRepo userRepo;

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private JwtService jwtService;
   
   private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
    
    public UserDto createUser(UserDto userDto)
    {
        userDto.setPassword(encoder.encode(userDto.getPassword())); 
        User user= userRepo.save(UserMapper.toUserEntity(userDto));
        return UserMapper.toUserDto(user);
    }

    public List<UserDto> getUsers()
    {
        return  userRepo.findAll().stream().map(UserMapper::toUserDto).collect(Collectors.toList());
    }

    public String verify(User user)  
    {
      try
      {

      Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));

      if(authentication.isAuthenticated())
      {
         User dbUser = userRepo.findByUserName(user.getUserName());
        return jwtService.generateToken(dbUser.getId());
      }
    }
    catch(Exception e)
    {
        return "Failed";
    }
      return "Unsuccesfull";
    }
}
