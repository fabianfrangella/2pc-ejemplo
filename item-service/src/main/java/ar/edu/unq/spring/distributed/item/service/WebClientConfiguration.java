package ar.edu.unq.spring.distributed.item.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfiguration {

    @Bean
    public WebClient coordinatorWebClient() {
       return WebClient.create("http://localhost:8080");
    }

}
