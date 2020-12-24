package com.example;

import com.example.annotation.EnableHelloWorld;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
//@ServletComponentScan("com.example.demo.web.servlet")
//@EnableCaching
@EnableHelloWorld
//@ConditionalOnProperty
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(Application.class)
                .web(WebApplicationType.NONE)
                .run(args);
//        SpringApplication.run(Application.class, args);
        // helloWorld Bean是否存在
        String helloWorld = context.getBean("helloWorld", String.class);
        System.out.println("helloWorld Bean :" + helloWorld);
        context.close();

    }

}
