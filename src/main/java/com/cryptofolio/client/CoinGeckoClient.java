package com.cryptofolio.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "coin-gecko-client", url = "${cryptofolio.client.coingecko.url}")
public interface CoinGeckoClient {

    @GetMapping
    Map<String, Map<String, Double>> getSimplePrice(
            @RequestParam String ids,
            @RequestParam("vs_currencies") String vsCurrencies,
            @RequestHeader("x-cg-pro-api-key") String apiKey
    );

}
