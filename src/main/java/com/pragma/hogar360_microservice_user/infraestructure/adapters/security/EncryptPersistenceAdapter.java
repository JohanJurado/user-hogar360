package com.pragma.hogar360_microservice_user.infraestructure.adapters.security;

import com.pragma.hogar360_microservice_user.domain.ports.out.IEncryptPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Transactional
@RequiredArgsConstructor
public class EncryptPersistenceAdapter implements IEncryptPersistencePort {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String password, String encryptPassword) {
        return passwordEncoder.matches(password, encryptPassword);
    }
}
