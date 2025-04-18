package com.pragma.hogar360_microservice_user.application.services;

import com.pragma.hogar360_microservice_user.application.dtos.request.LoginRequest;
import com.pragma.hogar360_microservice_user.application.dtos.response.LoginResponse;

public interface IAuthService {
    LoginResponse login(LoginRequest loginRequest);
}
