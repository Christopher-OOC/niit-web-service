package com.javalord.niit_web_service.niit_web_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {

    @GetMapping(value = "/test")
    public String test() {
        return "It is working";
    }
}
