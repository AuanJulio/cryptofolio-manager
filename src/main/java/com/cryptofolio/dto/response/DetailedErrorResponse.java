package com.cryptofolio.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record DetailedErrorResponse(
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp,
        Integer status,
        String error,
        String message,
        String path,
        Map<String, String> errors
) {
}
