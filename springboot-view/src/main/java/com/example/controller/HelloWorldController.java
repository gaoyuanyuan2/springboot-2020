package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {
    @GetMapping("hello-world")
    public String helloWorld(){
        return "hello-world";
    }
    @ModelAttribute("message")
    public String message(){
        return "HelloWorld";
    }

    @RequestMapping("")
    public String index(Model model, @RequestParam(required = false,defaultValue = "0") int value) {
        return "index";
    }
}
