package ar.edu.unq.spring.distributed.orchestrator.service.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean("personajeWebClient")
    public WebClient personajeWebClient() {
       return WebClient.create("http://localhost:8081");
    }

    @Bean("itemWebClient")
    public WebClient itemWebClient() {
        return WebClient.create("http://localhost:8082");
    }

    @Bean("storeWebClient")
    public WebClient storeWebClient() {
        return WebClient.create("http://localhost:8083");
    }
}
