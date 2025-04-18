package com.pragma.hogar360_microservice_user.domain.ports.out;

public interface IEncryptPersistencePort {
    String encode(String rawPassword);
    boolean matches(String password, String encryptPassword);
}