package com.chatcode.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private Info apiInfo() {
        return new Info()
                .title("ChatCode API")
                .description("ChatCode API Reference for Developers")
                .version("1.0.0");
    }

    @Bean
    public GroupedOpenApi exampleOpenApi() {
        return GroupedOpenApi.builder()
                .group("example")
                .pathsToMatch("/example/**")
                .build();
    }

    @Bean
    public GroupedOpenApi ArticleOpenApi() {
        return GroupedOpenApi.builder()
                .group("articles")
                .pathsToMatch("/articles/**")
                .build();
    }
}
