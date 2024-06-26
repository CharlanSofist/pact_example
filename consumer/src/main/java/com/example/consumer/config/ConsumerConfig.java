package com.example.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ConsumerConfig {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}