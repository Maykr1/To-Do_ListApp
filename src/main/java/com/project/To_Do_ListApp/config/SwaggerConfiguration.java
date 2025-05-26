package com.project.To_Do_ListApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("To Do List API")
                .version("v1")
                .description("REST endpoints for the To Do List")
                .termsOfService("https://example.com/terms")
                .license(new License().name("Apache 2.0").url("http://springdoc.org"))
            );
    }
}
