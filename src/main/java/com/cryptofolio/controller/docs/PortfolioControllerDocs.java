package com.cryptofolio.controller.docs;

import com.cryptofolio.dto.request.TransactionRequest;
import com.cryptofolio.dto.response.PortfolioResponse;
import com.cryptofolio.entity.Portfolio;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Portfolio", description = "Endpoints for managing assets and transactions")
@SecurityRequirement(name = "bearerAuth")
public interface PortfolioControllerDocs {

    @Operation(summary = "Add Transaction", description = "Records a new buy or sell transaction for the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction recorded successfully"),
            @ApiResponse(responseCode = "400", description = "Validation error or insufficient funds for selling"),
            @ApiResponse(responseCode = "404", description = "Coin ID not found in the external provider")
    })
    ResponseEntity<Portfolio> addTransaction(TransactionRequest request);


    @Operation(summary = "Get My Portfolio", description = "Retrieves the complete portfolio for the currently authenticated user, including real-time profit/loss calculations.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portfolio retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Portfolio not found")
    })
    ResponseEntity<PortfolioResponse> getPortfolioById();

}
