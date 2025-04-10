package com.pragma.hogar360_microservice_user.application.dtos.request;

public record LoginRequest(
        String email,
        String password
) {
}
