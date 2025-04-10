package com.pragma.hogar360_microservice_user.domain.usecases;

import com.pragma.hogar360_microservice_user.domain.model.UserModel;
import com.pragma.hogar360_microservice_user.domain.ports.in.IAuthServicePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IAuthPersistencePort;

import static com.pragma.hogar360_microservice_user.domain.utils.validations.UserValidations.validationByAuthAttributes;

public class AuthUseCase implements IAuthServicePort {

    private final IAuthPersistencePort authPersistencePort;

    public AuthUseCase(IAuthPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public String login(String email, String password) {
        validationByAuthAttributes(email, password);

        UserModel userModel = authPersistencePort.authenticate(email, password);
        return authPersistencePort.generateToken(userModel);
    }
}
