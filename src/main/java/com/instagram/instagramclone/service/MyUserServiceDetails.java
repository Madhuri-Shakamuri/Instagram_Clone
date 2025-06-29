package com.instagram.instagramclone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.instagram.instagramclone.Exception.UserNotFoundException;
import com.instagram.instagramclone.model.User;
import com.instagram.instagramclone.model.UserPrincipal;
import com.instagram.instagramclone.repository.UserRepo;

@Service
public class MyUserServiceDetails implements UserDetailsService{

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails  loadUserByUsername(String idAsString) throws UsernameNotFoundException
    {

    int id=Integer.parseInt(idAsString);

     User user=userRepo.findById(id)
                    .orElseThrow(()-> new UserNotFoundException("User Not Found"));

    
     return  new UserPrincipal(user);
    }
    
}
