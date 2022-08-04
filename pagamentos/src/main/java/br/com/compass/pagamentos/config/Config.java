package br.com.compass.pagamentos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Config {

    @Bean
    public WebClient.Builder builder() {
        return WebClient.builder();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
