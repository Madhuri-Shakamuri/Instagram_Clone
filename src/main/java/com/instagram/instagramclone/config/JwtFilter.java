package com.instagram.instagramclone.config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.instagram.instagramclone.service.JwtService;
import com.instagram.instagramclone.service.MyUserServiceDetails;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;

    @Autowired
    ApplicationContext context;
    @Override
    protected  void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException
            {
                //Bearer eshfyrighkddh...
                String authHeader=request.getHeader("Authorization");
                String token =null;
                String username=null;

                if(authHeader!=null && authHeader.startsWith("Bearer "))
                {
                    token=authHeader.substring(7);
                    username=jwtService.extractUserName(token);
                }
                if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
                {
                    UserDetails userdetails=context.getBean(MyUserServiceDetails.class).loadUserByUsername(username);
                    if(jwtService.validateToken(token,userdetails))
                    {
                         UsernamePasswordAuthenticationToken authToken=
                           new UsernamePasswordAuthenticationToken(userdetails,null,userdetails.getAuthorities());
                           authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                           SecurityContextHolder.getContext().setAuthentication(authToken); //adding it to the chain
                        }
                }
                filterChain.doFilter(request, response);
            }
    
}

