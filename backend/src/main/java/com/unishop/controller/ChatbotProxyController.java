package com.unishop.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/chatbot")
@CrossOrigin(origins = "*")
public class ChatbotProxyController {

    @Value("${app.ia-service.url:http://ia:8000}")
    private String iaServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/message")
    public ResponseEntity<String> proxyChatbotMessage(@RequestBody String message) {
        try {
            String url = iaServiceUrl + "/api/v1/chatbot/message";
            ResponseEntity<String> response = restTemplate.postForEntity(url, message, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                .body("{\"error\": \"Error connecting to IA service\"}");
        }
    }
}