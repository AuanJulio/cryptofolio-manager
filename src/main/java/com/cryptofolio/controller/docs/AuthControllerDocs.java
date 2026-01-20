package com.cryptofolio.controller.docs;

import com.cryptofolio.dto.request.LoginRequest;
import com.cryptofolio.dto.request.UserRequest;
import com.cryptofolio.dto.response.LoginResponse;
import com.cryptofolio.dto.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Authentication", description = "Endpoints for user registration and authentication")
public interface AuthControllerDocs {

    @Operation(summary = "Register a new user", description = "Creates a new user account in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data (e.g., missing fields)")
    })
    ResponseEntity<UserResponse> register(UserRequest request);

    @Operation(summary = "User Login", description = "Authenticates a user and returns a JWT token.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "400", description = "Invalid credentials (username or password)")
    })
    ResponseEntity<LoginResponse> login(LoginRequest request);

}
