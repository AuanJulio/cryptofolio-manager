package com.cryptofolio.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank(message = "Username cannot be blank")
        String username,

        @NotBlank(message = "E-mail cannot be blank")
        String email,

        @NotBlank(message = "Password cannot be blank")
        String password
) {
}
