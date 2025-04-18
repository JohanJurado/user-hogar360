package com.pragma.hogar360_microservice_user.infraestructure.exceptionshandler;

import java.time.LocalDateTime;

public record ExceptionResponse(String message, LocalDateTime dateTime) {
}
