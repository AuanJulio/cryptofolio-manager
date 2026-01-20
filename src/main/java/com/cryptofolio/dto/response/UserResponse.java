package com.cryptofolio.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Public user information")
public record UserResponse(
        @Schema(description = "Unique User ID", example = "65f3b4c8e4b0a1b2c3d4e5f6")
        String id,

        @Schema(description = "User's display name", example = "Auan Julio")
        String username,

        @Schema(description = "User's email address", example = "auan.julio@example.com")
        String email
) {
}
