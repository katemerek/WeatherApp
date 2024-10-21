package com.github.katemerek.WeatherApp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    String appDescription = "Rest service for recording measurements from a meteorological sensor. This project is made for educational purposes with Spring Boot. In the repository there is a rest client (RestClientForWeatherApp) for this Api.";

    @Bean
    public OpenAPI weatherAppOpenApi() {
        return new OpenAPI().info(new Info().title("WeatherApp")
                        .version("v1")
                        .description(appDescription)
                        .contact(new Contact().name("Ekaterina Merkulova")
                                .email("kate_merek@mail.ru")))
                .addServersItem(new Server().url("http://localhost:8080"));
    }

}
