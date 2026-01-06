package com.cryptofolio.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record PortfolioResponse(
        String id,
        BigDecimal totalInvested,
        BigDecimal totalValue,
        List<AssetResponse> assets
) {
}
