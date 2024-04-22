package com.training.shoppingCart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {


        UserDetails userDetails = User.builder().username("mithil").password(passwordEncoder().encode("test123")).roles("ADMIN").build();
        UserDetails userDetails1 = User.builder().username("sarthak").password(passwordEncoder().encode("wimp")).roles("ADMIN").build();
        UserDetails userDetails2 = User.builder().username("ONS").password(passwordEncoder().encode("training")).roles("USER").build();

        return new InMemoryUserDetailsManager(userDetails,userDetails1,userDetails2);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }
}
