package ru.utlc.clientmanagementservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient defaultWebClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://default-api-http://localhost:8080/api/v1")
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

//    @Bean
//    public WebClient customWebClient(WebClient.Builder builder) {
//        return builder
//                .baseUrl("http://custom-api-service.com")
//                .defaultHeader("Authorization", "Bearer someToken")
//                .build();
//    }
}
