package com.librarymanagment.LibraryManagment.config;

import com.flipkart.zjsonpatch.JsonPatch;
import tools.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }


}
