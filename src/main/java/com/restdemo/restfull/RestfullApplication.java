package com.restdemo.restfull;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RestfullApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestfullApplication.class, args);
    }

    @GetMapping("/goodbye")
    @RequestMapping("/")
    public String goodbye(){
        return "Welcome and goodbye to Springboot Rest APIs";
    }

}
