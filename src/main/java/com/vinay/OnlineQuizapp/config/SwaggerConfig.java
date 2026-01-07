package com.vinay.OnlineQuizapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI myCustomConfig() {
        return new OpenAPI()
                .info(
                        new Info().title("Online Quiz System").description("An Online Quiz System is a web application that enables students to take quizzes online with automated evaluation and instant results.")
                )
                .servers(List.of(
                        new Server().url("http://127.0.0.1:5500").description("Frontend Server"),
                        new Server().url("http://localhost:8080").description("Backend Server"))
                )
                .tags(List.of(
                        new Tag().name("Register Student APIs"),
                        new Tag().name("Question APIs"),
                        new Tag().name("Quiz APIs"),
                        new Tag().name("Student Login APIs"))
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization")
                ));
    }

}
