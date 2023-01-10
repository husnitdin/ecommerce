package com.micro.securityclient.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/welcome")
public class WelcomeController {

    @GetMapping("home")
    public ResponseEntity<String> hello(){
        return new ResponseEntity<>("hello from spring security home page", HttpStatus.OK);
    }

    @GetMapping("admin")
    public ResponseEntity<String> bye(){
        return new ResponseEntity<>("hello from spring security admin page", HttpStatus.OK);
    }
}