package com.pragma.hogar360_microservice_user.infraestructure.configurations.beans;

import com.pragma.hogar360_microservice_user.domain.ports.in.IUserServicePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IEncryptPersistencePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IRolePersistencePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IUserPersistencePort;
import com.pragma.hogar360_microservice_user.domain.usecases.UserUseCase;
import com.pragma.hogar360_microservice_user.infraestructure.adapters.persistence.EncryptPersistenceAdapter;
import com.pragma.hogar360_microservice_user.infraestructure.adapters.persistence.RolePersistenceAdapter;
import com.pragma.hogar360_microservice_user.infraestructure.adapters.persistence.UserPersistenceAdapter;
import com.pragma.hogar360_microservice_user.infraestructure.mappers.IRoleEntityMapper;
import com.pragma.hogar360_microservice_user.infraestructure.mappers.IUserEntityMapper;
import com.pragma.hogar360_microservice_user.infraestructure.repositories.mysql.IRoleRepository;
import com.pragma.hogar360_microservice_user.infraestructure.repositories.mysql.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfigurationUser {

    private final IRoleEntityMapper roleEntityMapper;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(), rolePersistencePort(), passwordEncoderPort(passwordEncoder()));
    }

    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserPersistenceAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RolePersistenceAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IEncryptPersistencePort passwordEncoderPort(BCryptPasswordEncoder passwordEncoder) {
        return new EncryptPersistenceAdapter(passwordEncoder);
    }
}

