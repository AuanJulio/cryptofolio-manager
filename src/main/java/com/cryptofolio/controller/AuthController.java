package com.cryptofolio.controller;

import com.cryptofolio.controller.docs.AuthControllerDocs;
import com.cryptofolio.dto.request.LoginRequest;
import com.cryptofolio.dto.request.UserRequest;
import com.cryptofolio.dto.response.LoginResponse;
import com.cryptofolio.dto.response.UserResponse;
import com.cryptofolio.entity.User;
import com.cryptofolio.exceptions.UsernameOrPasswordInvalidException;
import com.cryptofolio.mapper.UserMapper;
import com.cryptofolio.service.TokenService;
import com.cryptofolio.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthControllerDocs {

    private final TokenService tokenService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest request) {
        User user = userService.save(UserMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponse(user));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authentication = authenticationManager.authenticate(userAndPass);

            User user = (User) authentication.getPrincipal();

            String token = tokenService.generateToken(user);

            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(token));
        } catch (BadCredentialsException e) {
            throw new UsernameOrPasswordInvalidException("Invalid username or password.");
        }
    }

}
