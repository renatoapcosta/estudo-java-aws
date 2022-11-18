package br.com.ractecnologia.estudoaws.controller;

import br.com.ractecnologia.estudoaws.send.SQSSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private SQSSend sqsSend;

    @GetMapping
    public String get() {
        return "Hello";
    }

    @GetMapping("/home")
    public String home() {
        return "Home";
    }

    @GetMapping("/message")
    public String message(@RequestParam String message) {
        sqsSend.sendMessage(message, "http://sqs.us-east-1.localhost:4566/000000000000/ecs-singleshotbilling-buy-intent-dev");
        return "Home";
    }
}
