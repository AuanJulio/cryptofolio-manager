package com.cryptofolio.exceptions;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 404:
                return new ResourceNotFoundException("Coin not found");
            default:
                return new RuntimeException("Something went wrong");
        }
    }
}
