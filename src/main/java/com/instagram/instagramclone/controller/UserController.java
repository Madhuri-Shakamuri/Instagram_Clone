package com.instagram.instagramclone.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.instagramclone.dto.UserDto;
import com.instagram.instagramclone.model.User;
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
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto,HttpServletRequest request) throws JsonProcessingException
    {
        UserDto new_user= userService.createUser(userDto);

       String generatedToken = jwtService.generateToken(new_user.getUserName());

        
        ObjectMapper mapper=new ObjectMapper();
        String userjson=mapper.writeValueAsString(new_user);
        
       // CsrfToken csrftoken=(CsrfToken)request.getAttribute("_csrf");
        String response="User Created Successfully\n"
                          +userjson
                          +"\nToken: "+generatedToken;//+"\nCsrfToken: "+csrftoken.getToken();
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
