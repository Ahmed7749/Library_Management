package com.librarymanagment.LibraryManagment.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        return http.authorizeHttpRequests(auth ->
                auth.requestMatchers("/authors/**","/categories/**").hasRole("ADMIN")
                        .requestMatchers("/books/**").hasRole("USER")
                        .requestMatchers("/","/**").permitAll())
                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/", true))
                .build();
    }
}
