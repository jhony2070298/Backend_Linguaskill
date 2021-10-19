package com.mintic.api_mintic.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/candidatos")
public class UserController {

    @GetMapping
    public String getUser(){
        
        return "get user";
    }

    @PostMapping
    public String createUser(){

        return "Create user";
    }
}
