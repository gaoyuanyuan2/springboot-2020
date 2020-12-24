package com.example.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 引导类
 */
@SpringBootApplication(scanBasePackages = "com.example")
public class SpringbootMvcBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootMvcBootstrap.class);
    }
}
