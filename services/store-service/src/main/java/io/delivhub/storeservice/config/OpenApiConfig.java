package io.delivhub.storeservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("DelivHub Store Service API")
                        .version("1.0.0")
                        .description("REST API для управления товарами и категориями")
                        .contact(new Contact()
                                .name("DelivHub Team")
                                .email("support@delivhub.io")));
    }
}
