package com.example.bono_challenge.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Custom Field and Rule API")
                        .version("1.0.0")
                        .description("APIs to manage Custom Fields, Custom Field Values, and Rules for entities.")
                );
    }
}
