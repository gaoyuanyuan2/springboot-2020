package com.example.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example")
public class SpringbootViewBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootViewBootstrap.class,args);
    }
}
