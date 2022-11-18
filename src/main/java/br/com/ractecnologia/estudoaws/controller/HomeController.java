package br.com.ractecnologia.estudoaws.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String get() {
        return "Hello";
    }

    @GetMapping("/home")
    public String home() {
        return "Home";
    }
}
