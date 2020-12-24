package com.example.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({HelloWorldImportSelector.class})
//@Import(HelloWorldConfig.class)
public @interface EnableHelloWorld {
}
