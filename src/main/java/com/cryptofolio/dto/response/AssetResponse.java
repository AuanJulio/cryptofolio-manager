package com.cryptofolio.dto.response;

import java.math.BigDecimal;

public record AssetResponse(
        String symbol,
        BigDecimal quantity,
        BigDecimal averagePrice,
        BigDecimal currentPrice,
        BigDecimal totalValue,
        BigDecimal profitLossPercentage
) {
}
