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

    @Bean
    public GroupedOpenApi OpinionOpenApi() {
        return GroupedOpenApi.builder()
                .group("opinions")
                .pathsToMatch("/opinions/**")
                .build();
    }

    @Bean
    public GroupedOpenApi FileOpenApi() {
        return GroupedOpenApi.builder()
                .group("files")
                .pathsToMatch("/files/**")
                .build();
    }

    @Bean
    public GroupedOpenApi CategoryOpenApi() {
        return GroupedOpenApi.builder()
                .group("categories")
                .pathsToMatch("/categories/**")
                .build();
    }

    @Bean
    public GroupedOpenApi AvatarOpenApi() {
        return GroupedOpenApi.builder()
                .group("avatars")
                .pathsToMatch("/avatars/**")
                .build();
    }
}
