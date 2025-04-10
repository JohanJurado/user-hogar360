package com.pragma.hogar360_microservice_user.domain.ports.out;

import com.pragma.hogar360_microservice_user.domain.model.UserModel;

import java.util.Optional;

public interface IUserPersistencePort {

    void save(UserModel userModel);
    Optional<UserModel> findByDocument(String document);
    Optional<UserModel> findByEmail(String email);
}
