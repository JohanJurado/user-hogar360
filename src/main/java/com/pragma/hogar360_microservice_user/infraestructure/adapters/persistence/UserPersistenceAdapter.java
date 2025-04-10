package com.pragma.hogar360_microservice_user.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_user.domain.model.UserModel;
import com.pragma.hogar360_microservice_user.domain.ports.out.IUserPersistencePort;
import com.pragma.hogar360_microservice_user.infraestructure.entities.UserEntity;
import com.pragma.hogar360_microservice_user.infraestructure.mappers.IUserEntityMapper;
import com.pragma.hogar360_microservice_user.infraestructure.repositories.mysql.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserPersistenceAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void save(UserModel userModel) {
        userRepository.save(userEntityMapper.modelToEntity(userModel));
    }

    @Override
    public Optional<UserModel> findByDocument(String document) {
        return userEntityMapper.entityOptionalToModelOptional(userRepository.findByDocument(document));
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        return userEntityMapper.entityOptionalToModelOptional(userRepository.findByEmail(email));
    }
}
