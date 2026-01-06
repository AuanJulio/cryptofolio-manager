package com.cryptofolio.dto.request;

import com.cryptofolio.dto.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionRequest(

        @NotNull(message = "Coin id cannot be null")
        String coinId,

        @NotNull(message = "Symbol cannot be null")
        String symbol,

        @Positive(message = "Quantity must be positive")
        BigDecimal quantity,

        @Positive(message = "Price must be positive")
        BigDecimal pricePerUnit,

        @NotNull(message = "Type cannot be null")
        TransactionType type

) {
}
