package org.example.filterchaindemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String home() {
        return ":::::::::::::::::::::::OK::::::::::::::::::::::::";
    }
}
