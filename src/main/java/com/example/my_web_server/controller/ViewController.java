package com.example.my_web_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ViewController {

    @GetMapping("/hello")
    public String hello() {
        return "Hi!";
    }
}
