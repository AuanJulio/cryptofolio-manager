package com.cryptofolio.entity;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssetItem {

    private String coinId;

    private String symbol;

    private BigDecimal quantity;

    private BigDecimal averagePrice;

}
