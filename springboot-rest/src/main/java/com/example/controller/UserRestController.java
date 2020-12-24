package com.example.controller;

import com.example.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @PostMapping(value = "echo/user",produces = "application/json;charset=UTF-8"
            ,consumes = "application/*;charset=UTF-8")
    public User user(@RequestBody User user) {
        return user;
    }
}
