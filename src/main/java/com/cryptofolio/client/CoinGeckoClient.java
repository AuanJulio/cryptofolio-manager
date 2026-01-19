package com.cryptofolio.client;

import com.cryptofolio.exceptions.CustomErrorDecoder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "coin-gecko-client", url = "${cryptofolio.client.coingecko.url}", configuration = {CustomErrorDecoder.class})
public interface CoinGeckoClient {

    @GetMapping("/simple/price")
    Map<String, Map<String, Double>> getSimplePrice(
            @RequestParam String ids,
            @RequestParam("vs_currencies") String vsCurrencies,
            @RequestHeader("x-cg-demo-api-key") String apiKey
    );

    @GetMapping("/coins/{id}")
    Map<String, Object> getCoin(
            @PathVariable String id,
            @RequestHeader("x-cg-demo-api-key") String apiKey
    );

}
