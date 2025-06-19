package com.instagram.instagramclone.config;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.instagram.instagramclone.service.JwtService;
import com.instagram.instagramclone.service.MyUserServiceDetails;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JwtService jwtService;

    @Autowired
    private MyUserServiceDetails userServiceDetails;
    @Override
    protected  void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException
            {
                //Bearer eshfyrighkddh...
                String authHeader=request.getHeader("Authorization");
                String token =null;
                Integer userId=null;

                
                    if (authHeader != null && authHeader.startsWith("Bearer ")) {
                     token = authHeader.substring(7);

                      try {
                           userId = jwtService.extractUserId(token);
                      } 
                      catch (ExpiredJwtException ex) {
                               response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                               response.getWriter().write("Token expired. Please login again.");
                               return; 
                     } catch (Exception e) {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                         response.getWriter().write("Invalid token.");
                          return;
                        }
                    }
                
                if(userId!=null && SecurityContextHolder.getContext().getAuthentication()==null)
                {
                    UserDetails userdetails = userServiceDetails.loadUserByUsername(String.valueOf(userId));

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

