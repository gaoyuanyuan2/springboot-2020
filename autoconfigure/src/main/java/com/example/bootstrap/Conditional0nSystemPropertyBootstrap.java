package com.example.bootstrap;

import com.example.condition.ConditionalOnSystemProperty;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

public class Conditional0nSystemPropertyBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(Conditional0nSystemPropertyBootstrap.class)
                        .web(WebApplicationType.NONE)
                        .run(args);
        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println("helloWorld Bean :" + helloWorld);
        context.close();
    }

    @Bean
    @ConditionalOnSystemProperty(name = "user.name", value = "Y" )
    public String helloWorld() {
        return "Hello, World Y";
    }

}
