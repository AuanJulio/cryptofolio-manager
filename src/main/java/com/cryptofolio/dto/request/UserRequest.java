package com.cryptofolio.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Data required for user registration")
public record UserRequest(

        @Schema(description = "Full name or display name", example = "Auan Julio")
        @NotBlank(message = "Username cannot be blank")
        String username,

        @Schema(description = "Valid email address", example = "auan.julio@example.com")
        @NotBlank(message = "E-mail cannot be blank")
        @Email(message = "Invalid email address")
        String email,

        @Schema(description = "Password for the account", example = "strongPassword123")
        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
