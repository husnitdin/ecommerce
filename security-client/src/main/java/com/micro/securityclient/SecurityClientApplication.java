package com.micro.securityclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.micro.securityclient.*")
public class SecurityClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(SecurityClientApplication.class, args);
    }
}