package com.pragma.hogar360_microservice_user.application.dtos.request;

import java.time.LocalDate;

 public record UserRequest(String name, String lastName, String document, String phoneNumber, LocalDate birthdate, String email, String password) {
}
