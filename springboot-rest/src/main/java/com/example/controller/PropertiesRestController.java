package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

//@RestController
@Controller
public class PropertiesRestController {
    @PostMapping(value = "add/props"
            , consumes = "text/properties;charset=UTF-8"
    )
    public Properties addProperties(
//            @RequestBody
            Properties properties) {
        return properties;
    }
}
