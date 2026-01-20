package com.cryptofolio.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CryptoFolio Manager API",
                version = "v1",
                description = "RESTful API for managing cryptocurrency portfolios. It allows users to track assets, record transactions, and calculate real-time profits based on market prices.",
                contact = @Contact(
                        name = "Auan Julio Galvão dos Santos",
                        email = "auanjulio13@gmail.com",
                        url = "https://github.com/AuanJulio"
                )
        )
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Authorization header using the Bearer scheme. Example: \"Authorization: Bearer {token}\"",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}
