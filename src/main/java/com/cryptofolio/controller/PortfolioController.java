package com.cryptofolio.controller;

import com.cryptofolio.dto.request.TransactionRequest;
import com.cryptofolio.dto.response.PortfolioResponse;
import com.cryptofolio.dto.response.UserResponse;
import com.cryptofolio.entity.Portfolio;
import com.cryptofolio.service.PortfolioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping("/transaction")
    public ResponseEntity<Portfolio> addTransaction(@RequestBody @Valid TransactionRequest request) {
        UserResponse user = getAuthenticatedUser();

        Portfolio portfolio = portfolioService.addTransaction(user.id(), request);
        return ResponseEntity.status(HttpStatus.CREATED).body(portfolio);
    }

    @GetMapping
    public ResponseEntity<PortfolioResponse> getPortfolioById() {
        UserResponse user = getAuthenticatedUser();

        PortfolioResponse portfolioResponse = portfolioService.getPortfolioById(user.id());
        return ResponseEntity.ok(portfolioResponse);
    }

    private UserResponse getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserResponse) authentication.getPrincipal();
    }

}
