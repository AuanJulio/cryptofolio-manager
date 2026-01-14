package com.cryptofolio.controller;

import com.cryptofolio.dto.request.TransactionRequest;
import com.cryptofolio.dto.response.PortfolioResponse;
import com.cryptofolio.entity.Portfolio;
import com.cryptofolio.service.PortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping("/{id}/transaction")
    public ResponseEntity<Portfolio> addTransaction(@PathVariable String id, @RequestBody @Valid TransactionRequest request) {
        Portfolio portfolio = portfolioService.addTransaction(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(portfolio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PortfolioResponse> getPortfolioById(@PathVariable String id) {
        PortfolioResponse portfolioResponse = portfolioService.getPortfolioById(id);
        return ResponseEntity.ok(portfolioResponse);
    }

}
