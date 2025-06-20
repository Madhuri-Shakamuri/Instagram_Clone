package com.instagram.instagramclone.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.instagramclone.dto.UserDto;
import com.instagram.instagramclone.response.UserResponse;
import com.instagram.instagramclone.service.JwtService;
import com.instagram.instagramclone.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {


    @Mock
    private UserService userService;

    @Mock 
    private JwtService jwtService;

     @Mock
    private HttpServletRequest request;


    @InjectMocks
    private UserController userController;


    @Test
    void testCreateUser_success() throws JsonProcessingException
    {
        UserDto input =new UserDto(0,"testuser","testuser@123");
        UserDto savedUser=new UserDto(101,"testuser",null);
        String token="mock-token";


        when(userService.createUser(input)).thenReturn(savedUser);
        when(jwtService.generateToken(savedUser.getId())).thenReturn(token);

        ResponseEntity<UserResponse> response=userController.createUser(input,request);


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User Created Succesfully", response.getBody().getMessage());
        assertEquals("testuser", response.getBody().getUser().getUserName());
        assertEquals("mock-token", response.getBody().getToken());
           
        verify(userService,times(1)).createUser(input);
        verify(jwtService, times(1)).generateToken(savedUser.getId());


    }
    
}
