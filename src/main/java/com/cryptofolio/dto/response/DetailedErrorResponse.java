package com.cryptofolio.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Schema(description = "Detailed error structure for validation failures (Bad Request)")
public record DetailedErrorResponse(

        @Schema(description = "Timestamp of the error", example = "20/03/2024 10:30:00", type = "string")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp,

        @Schema(description = "HTTP Status Code", example = "400")
        Integer status,

        @Schema(description = "HTTP Error Reason", example = "Bad Request")
        String error,

        @Schema(description = "General error message", example = "Validation failed due to invalid request parameters.")
        String message,

        @Schema(description = "Request path", example = "/auth/register")
        String path,

        @Schema(description = "Map of field names and specific validation errors", example = "{\"email\": \"Email cannot be blank\", \"password\": \"must be greater than 6 characters\"}")
        Map<String, String> errors
) {
}
