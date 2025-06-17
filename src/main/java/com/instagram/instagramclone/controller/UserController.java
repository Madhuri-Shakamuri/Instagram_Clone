package com.instagram.instagramclone.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.instagramclone.dto.UserDto;
import com.instagram.instagramclone.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
// @RequestMapping("/api")
public class UserController 
{
    @Autowired
    private UserService userService;


//    @GetMapping("/")
//     public String greet(HttpServletRequest request)
//     {
//       System.out.println("Users Controller get method ");
//       return "Session Id :\n"+request.getSession().getId();
//     }

    // @GetMapping("/csrf-token")
    // public CsrfToken getCsrfToken(HttpServletRequest request)
    // {
    //  return (CsrfToken)request.getAttribute("_csrf");
    // }

    @GetMapping("/users")
    public List<UserDto> getUsers()
    {
        return  userService.getUsers();
    }
    
    @PostMapping("/user")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto,HttpServletRequest request) throws JsonProcessingException
    {
        UserDto new_user= userService.createUser(userDto);
        ObjectMapper mapper=new ObjectMapper();
        String userjson=mapper.writeValueAsString(new_user);
       // CsrfToken csrftoken=(CsrfToken)request.getAttribute("_csrf");
        String response="User Created Successfully\n"+userjson;//+"\nCsrfToken: "+csrftoken.getToken();
        return ResponseEntity.ok(response);
    }

    
    
}
