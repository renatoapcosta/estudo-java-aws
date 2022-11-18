package br.com.ractecnologia.estudoaws.controller;

import br.com.ractecnologia.estudoaws.publish.PublishSNS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private PublishSNS publishSNS;

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
        log.info("Enviando mensagem: {} para SNS", message);
        Map<String, MessageAttributeValue> messageAttr = new HashMap<>();
        messageAttr.put("action",
                MessageAttributeValue.builder().dataType("String")
                        .stringValue("generate_box_report").build());
        publishSNS.sendMessage(message, "arn:aws:sns:us-east-1:000000000000:ecs-singleshotbilling-orquestrator-dev", messageAttr);
        return "Mensagem enviada SNS";
    }
}
