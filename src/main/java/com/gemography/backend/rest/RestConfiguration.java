package com.gemography.backend.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;


@Configuration
public class RestConfiguration {

	private ObjectMapper jacksonObjectMapper() {
        return new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

   @Bean
    public WebClient webClient() {
	   ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
			    .codecs(configurer -> configurer.defaultCodecs()
			    .jackson2JsonDecoder(new Jackson2JsonDecoder(jacksonObjectMapper())))
			    .build();
	   WebClient webClient = WebClient.builder()
    			.baseUrl("https://api.github.com/")
    			.exchangeStrategies(exchangeStrategies)
    			.build();
        return webClient;
    }
  
}
