package com.pragma.hogar360_microservice_user.domain.ports.out;

import com.pragma.hogar360_microservice_user.domain.model.UserModel;

public interface IAuthPersistencePort {

    UserModel authenticate(String email, String password);
    String generateToken(UserModel userModel);
}
