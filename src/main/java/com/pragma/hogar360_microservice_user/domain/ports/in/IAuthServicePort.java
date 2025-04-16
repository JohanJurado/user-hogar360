package com.pragma.hogar360_microservice_user.domain.ports.in;

public interface IAuthServicePort {
    String login(String email, String password);
}
