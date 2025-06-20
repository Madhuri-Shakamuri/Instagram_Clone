package com.instagram.instagramclone.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.instagram.instagramclone.dto.UserDto;
import com.instagram.instagramclone.response.UserResponse;
import com.instagram.instagramclone.service.JwtService;
import com.instagram.instagramclone.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UserController 
{
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/users")
    public List<UserDto> getUsers()
    {
        return  userService.getUsers();
    }
    
    @PostMapping("/user")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserDto userDto,HttpServletRequest request) throws JsonProcessingException
    {
        UserDto newUser= userService.createUser(userDto);

      String token = jwtService.generateToken(newUser.getId());

        
       // CsrfToken csrftoken=(CsrfToken)request.getAttribute("_csrf");
        UserResponse response=new UserResponse("User Created Succesfully", newUser, token) ;                                           //+"\nCsrfToken: "+csrftoken.getToken();

        return ResponseEntity.ok(response);
    }    
    
}




/*   @GetMapping("/")
    public String greet(HttpServletRequest request)
    {
      System.out.println("Users Controller get method ");
       return "Session Id :\n"+request.getSession().getId();
    }

    @GetMapping("/csrf-token")
     public CsrfToken getCsrfToken(HttpServletRequest request)
    {
     return (CsrfToken)request.getAttribute("_csrf");
    }

 @PostMapping("/login")
    public String login(@RequestBody User user)
    {
        
        return  userService.verify(user);
    } */
