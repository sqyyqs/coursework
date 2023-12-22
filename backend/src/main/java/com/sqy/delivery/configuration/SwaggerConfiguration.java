package com.sqy.delivery.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI deliveryApi() {
        return new OpenAPI()
                .info(new Info().title("деливери апп")
                        .version("1.0")
                        .license(new License().name("MIT License").url("https://www.mit.edu/~amini/LICENSE.md")))
                .externalDocs(new ExternalDocumentation()
                        .url("https://www.google.com"));
    }
}
