package com.example.products.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class WelcomeController {

    @GetMapping ("hello")
    public String welcome(){
        return "Welcome Page";
    }

}