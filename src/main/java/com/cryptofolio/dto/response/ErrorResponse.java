package com.cryptofolio.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Schema(description = "Standard error structure for API exceptions")
public record ErrorResponse(

        @Schema(description = "Timestamp of the error", example = "20/03/2024 10:30:00", type = "string")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp,

        @Schema(description = "HTTP Status Code", example = "404")
        Integer status,

        @Schema(description = "HTTP Error Reason", example = "Not Found")
        String error,

        @Schema(description = "Detailed error message", example = "Coin not found in external provider")
        String message,

        @Schema(description = "Request path that generated the error", example = "/portfolio/transaction")
        String path
) {
}
