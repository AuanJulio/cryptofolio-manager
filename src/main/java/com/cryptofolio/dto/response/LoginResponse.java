package com.cryptofolio.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response object containing the authentication token")
public record LoginResponse(

        @Schema(description = "JWT Access Token. Must be sent in the 'Authorization' header as 'Bearer {token}'",
                example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c2VyQGVtYWlsLmNvbSIsImlhdCI6MTYxNjIzOTAyMn0...")
        String token) {
}
