package com.example.configuration;

import org.springframework.context.annotation.Bean;

//@Configuration
public class HelloWorldConfiguration {
    @Bean
    public String helloWorld(){
        return "Hello World,2020";
    }
}
