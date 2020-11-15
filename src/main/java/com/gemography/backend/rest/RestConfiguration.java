package com.gemography.backend.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
/**
* Beans creation for dependency injection
*
* @author  Youness EL AACHIQ
* @version 1.0
* @since   2020-11-14
*/

@Configuration
public class RestConfiguration {

	private ObjectMapper jacksonObjectMapper() {
        return new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

	// configure web client to containt the base URL of github api
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
