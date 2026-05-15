package org.sopt.sopkathon_server.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Value("${spring.swagger.base-url:http://localhost:8080}")
    private String baseUrl;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("SOPKATHON API")
                        .description("SOPKATHON WEB1 API Documentation")
                        .version("v1.0.0"))
                .servers(List.of(
                        new Server().url("http://3.34.14.5:8080").description("Production Server"),
                        new Server().url("http://localhost:8080").description("Local Server")
                ));
    }
}
