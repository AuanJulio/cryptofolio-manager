package com.cryptofolio.service;

import com.cryptofolio.client.CoinGeckoClient;
import com.cryptofolio.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CoinGeckoService {

    private final CoinGeckoClient coinGeckoClient;

    @Value("${cryptofolio.client.coingecko.key}")
    private String apiKey;

    @Cacheable(value = "prices", key = "#coinId")
    public BigDecimal getPrice(String coinId) {

        Map<String, Map<String, Double>> response = coinGeckoClient.getSimplePrice(
                coinId, "usd", apiKey
        );

        if(!ObjectUtils.isEmpty(response) && response.containsKey(coinId)) {
            Map<String, Double> currencyMap = response.get(coinId);

            if (currencyMap.containsKey("usd")) {
                return BigDecimal.valueOf(currencyMap.get("usd"));
            }
        }

        throw new ResourceNotFoundException("Price not found");

    }

    public void validateCoinExists(String coinId) {
        coinGeckoClient.getCoin(coinId, apiKey);
    }
}
