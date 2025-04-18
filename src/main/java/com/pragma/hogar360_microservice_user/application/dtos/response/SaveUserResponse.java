package com.pragma.hogar360_microservice_user.application.dtos.response;

import java.time.LocalDateTime;

public record SaveUserResponse(String message, LocalDateTime localDateTime) {
}
