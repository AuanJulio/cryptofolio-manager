package com.cryptofolio.dto.request;

import com.cryptofolio.dto.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Payload to record a new transaction")
public record TransactionRequest(

        @Schema(description = "The CoinGecko API ID for the cryptocurrency", example = "bitcoin")
        @NotNull(message = "Coin id cannot be null")
        String coinId,

        @Schema(description = "The symbol of the coin", example = "BTC")
        @NotNull(message = "Symbol cannot be null")
        String symbol,

        @Schema(description = "The amount of coins bought or sold", example = "0.5")
        @Positive(message = "Quantity must be positive")
        BigDecimal quantity,

        @Schema(description = "The price paid per unit at the time of transaction", example = "50000.00")
        @Positive(message = "Price must be positive")
        BigDecimal pricePerUnit,

        @Schema(description = "Type of transaction (BUY or SELL)", example = "BUY")
        @NotNull(message = "Type cannot be null")
        TransactionType type

) {
}
