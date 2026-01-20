package com.cryptofolio.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Details of a specific cryptocurrency asset within the portfolio")
public record AssetResponse(

        @Schema(description = "The symbol of the cryptocurrency", example = "BTC")
        String symbol,

        @Schema(description = "Total quantity held", example = "0.5")
        BigDecimal quantity,

        @Schema(description = "Weighted average price paid per unit (Cost basis)", example = "50000.00")
        BigDecimal averagePrice,

        @Schema(description = "Current market price per unit (Real-time from CoinGecko)", example = "65000.00")
        BigDecimal currentPrice,

        @Schema(description = "Total current value of this asset (Quantity * Current Price)", example = "32500.00")
        BigDecimal totalValue,

        @Schema(description = "Profit or Loss percentage regarding the average price", example = "30.00")
        BigDecimal profitLossPercentage
) {
}
