package com.pragma.hogar360_microservice_user.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_user.domain.ports.out.IEncryptPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class EncryptPersistenceAdapter implements IEncryptPersistencePort {

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}
