package com.pragma.hogar360_microservice_user.application.services.impl;

import com.pragma.hogar360_microservice_user.application.dtos.request.LoginRequest;
import com.pragma.hogar360_microservice_user.application.dtos.response.LoginResponse;
import com.pragma.hogar360_microservice_user.application.services.IAuthService;
import com.pragma.hogar360_microservice_user.domain.ports.in.IAuthServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.pragma.hogar360_microservice_user.application.utils.ApplicationConstants.LOG_IN_AUTHENTICATE_RESPONSE;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final IAuthServicePort authServicePort;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String token = authServicePort.login(loginRequest.email(), loginRequest.password());
        return new LoginResponse(LOG_IN_AUTHENTICATE_RESPONSE, token);
    }
}
