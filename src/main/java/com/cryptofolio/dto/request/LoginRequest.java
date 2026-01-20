package com.cryptofolio.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Credentials for user login")
public record LoginRequest(

        @Schema(description = "User's registered email", example = "auan.julio@example.com")
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email address")
        String email,

        @Schema(description = "User's password", example = "123456")
        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
