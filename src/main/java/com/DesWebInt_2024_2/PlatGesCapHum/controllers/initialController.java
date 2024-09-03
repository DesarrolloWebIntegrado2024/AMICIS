package com.DesWebInt_2024_2.PlatGesCapHum.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class initialController {

    @GetMapping("/hello")
    public String holas(){
        return "Hello world";
    }
}
