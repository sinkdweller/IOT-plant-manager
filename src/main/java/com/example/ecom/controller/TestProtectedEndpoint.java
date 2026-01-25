package com.example.ecom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestProtectedEndpoint {
    @GetMapping("/testEnd")
    public String testEndpoint(){
        return "received!!!";

    }
}
