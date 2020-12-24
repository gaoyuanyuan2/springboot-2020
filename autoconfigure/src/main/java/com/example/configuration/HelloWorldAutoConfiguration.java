package com.example.configuration;

import com.example.annotation.EnableHelloWorld;
import com.example.condition.ConditionalOnSystemProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHelloWorld
@ConditionalOnSystemProperty(name = "user.name", value = "Y" )
public class HelloWorldAutoConfiguration {
}
