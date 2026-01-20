package com.cryptofolio.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Complete portfolio summary with aggregated values")
public record PortfolioResponse(

        @Schema(description = "User ID owner of this portfolio", example = "65f3b4c8e4b0a1b2c3d4e5f6")
        String id,

        @Schema(description = "Total amount invested (Sum of all assets cost basis)", example = "25000.00")
        BigDecimal totalInvested,

        @Schema(description = "Total current value of the portfolio (Sum of all assets current value)", example = "32500.00")
        BigDecimal totalValue,

        @Schema(description = "List of assets held in the portfolio")
        List<AssetResponse> assets
) {
}
