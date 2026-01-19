package com.cryptofolio.service;

import com.cryptofolio.client.CoinGeckoClient;
import com.cryptofolio.dto.TransactionType;
import com.cryptofolio.dto.request.TransactionRequest;
import com.cryptofolio.dto.response.AssetResponse;
import com.cryptofolio.dto.response.PortfolioResponse;
import com.cryptofolio.entity.AssetItem;
import com.cryptofolio.entity.Portfolio;
import com.cryptofolio.exceptions.ResourceNotFoundException;
import com.cryptofolio.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final CoinGeckoService coinGeckoService;

    @Value("${cryptofolio.client.coingecko.key}")
    private String apiKey;

    public Portfolio addTransaction(String userId, TransactionRequest request) {

        coinGeckoService.validateCoinExists(request.coinId());

        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElse(Portfolio
                        .builder()
                        .userId(userId)
                        .assets(new ArrayList<>())
                        .build());

        Optional<AssetItem> optAssetItem = portfolio.getAssets().stream()
                .filter(assetItem -> assetItem.getCoinId().equals(request.coinId()))
                .findFirst();

        AssetItem assetItem;

        if(request.type().equals(TransactionType.BUY)) {
            if(optAssetItem.isPresent()) {
                assetItem = optAssetItem.get();

                BigDecimal currentPrice = assetItem.getQuantity().multiply(assetItem.getAveragePrice());
                BigDecimal newPrice = request.quantity().multiply(request.pricePerUnit());
                BigDecimal newQuantity = assetItem.getQuantity().add(request.quantity());

                BigDecimal newAveragePrice = currentPrice.add(newPrice)
                        .divide(newQuantity, 2, BigDecimal.ROUND_HALF_UP);

                assetItem.setQuantity(newQuantity);
                assetItem.setAveragePrice(newAveragePrice);
            } else {
                assetItem = AssetItem.builder()
                        .coinId(request.coinId())
                        .symbol(request.symbol())
                        .quantity(request.quantity())
                        .averagePrice(request.pricePerUnit())
                        .build();
                portfolio.getAssets().add(assetItem);
            }
        } else if(request.type().equals(TransactionType.SELL)) {
            if(optAssetItem.isEmpty()) {
                throw new ResourceNotFoundException("Asset not found");
            }

            assetItem = optAssetItem.get();

            if(assetItem.getQuantity().compareTo(request.quantity()) < 0) {
                throw new ResourceNotFoundException("Not enough quantity");
            }

            BigDecimal newQuantity = assetItem.getQuantity().subtract(request.quantity());

            if(newQuantity.compareTo(BigDecimal.ZERO) == 0) {
                portfolio.getAssets().remove(assetItem);
            } else {
                assetItem.setQuantity(newQuantity);
            }
        }

        portfolio.updateTimestamps();
        return portfolioRepository.save(portfolio);
    }

    public PortfolioResponse getPortfolioById(String userId) {

        Portfolio portfolio = portfolioRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Portfolio not found"));

        List<AssetResponse> assetResponses = new ArrayList<>();
        BigDecimal totalInvested = BigDecimal.ZERO;
        BigDecimal totalValue = BigDecimal.ZERO;

        for (AssetItem assetItem : portfolio.getAssets()) {
            BigDecimal currentValue = coinGeckoService.getPrice(assetItem.getCoinId());
            BigDecimal currentAssetValue = assetItem.getQuantity().multiply(currentValue);
            BigDecimal investedValue = assetItem.getQuantity().multiply(assetItem.getAveragePrice());

            BigDecimal profitLoss = BigDecimal.ZERO;
            if(assetItem.getAveragePrice().compareTo(BigDecimal.ZERO) > 0) {
                profitLoss = currentValue.subtract(assetItem.getAveragePrice())
                        .divide(assetItem.getAveragePrice(), 2, BigDecimal.ROUND_HALF_UP)
                        .multiply(BigDecimal.valueOf(100));
            }

            assetResponses.add(new AssetResponse(
                    assetItem.getSymbol(),
                    assetItem.getQuantity(),
                    assetItem.getAveragePrice(),
                    currentValue,
                    currentAssetValue,
                    profitLoss
            ));

            totalValue = totalValue.add(currentAssetValue);
            totalInvested = totalInvested.add(investedValue);
        }

        return new PortfolioResponse(
                userId,
                totalInvested,
                totalValue,
                assetResponses
        );

    }

}
