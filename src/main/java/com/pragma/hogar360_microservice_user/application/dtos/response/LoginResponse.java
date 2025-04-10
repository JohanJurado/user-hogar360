package com.pragma.hogar360_microservice_user.application.dtos.response;

public record LoginResponse(
        String email,
        String jwt
) {
}
