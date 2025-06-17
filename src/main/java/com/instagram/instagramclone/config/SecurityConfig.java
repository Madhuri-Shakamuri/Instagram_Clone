package com.instagram.instagramclone.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // disabling spring default security - login 
public class SecurityConfig 
{

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {
        return
        http
            .csrf(customizer ->customizer.disable())                                  //diableing csrf for every request
            .authorizeHttpRequests(request ->request.anyRequest().authenticated())    // enable authentication for every request
            //http.formLogin(Customizer.withDefaults());                             //  enabling the password and username enable and give response as form
            .httpBasic(Customizer.withDefaults())                                    //  instead of form we get the response in postman
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // customized session id 
            .build();                                                                // returns the object of SFC
    }

    // @Bean 
    // public UserDetailsService userDetailsService()
    // {
    //     UserDetails user1=User.withDefaultPasswordEncoder()
    //                           .username("bannu")
    //                           .password("b@123")
    //                           .roles("user")
    //                           .build();
    //        return new InMemoryUserDetailsManager(user1);                   
    // }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService); 
    provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    return provider;
   }

    
    
}
