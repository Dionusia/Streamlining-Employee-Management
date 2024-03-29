package com.example.intro.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //allow CORS for all paths
                .allowedOrigins("http://localhost:8080") //allow requests from  that origin
                .allowedMethods("GET", "POST", "PUT", "DELETE"); //allowed HTTP methods
    }
}