package com.pragma.hogar360_microservice_user.infraestructure.configurations;

import com.pragma.hogar360_microservice_user.domain.ports.in.IAuthServicePort;
import com.pragma.hogar360_microservice_user.domain.ports.in.IUserServicePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IAuthPersistencePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IEncryptPersistencePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IRolePersistencePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IUserPersistencePort;
import com.pragma.hogar360_microservice_user.domain.usecases.AuthUseCase;
import com.pragma.hogar360_microservice_user.domain.usecases.UserUseCase;
import com.pragma.hogar360_microservice_user.infraestructure.adapters.security.AuthPersistenceAdapter;
import com.pragma.hogar360_microservice_user.infraestructure.adapters.security.EncryptPersistenceAdapter;
import com.pragma.hogar360_microservice_user.infraestructure.adapters.persistence.RolePersistenceAdapter;
import com.pragma.hogar360_microservice_user.infraestructure.adapters.persistence.UserPersistenceAdapter;
import com.pragma.hogar360_microservice_user.infraestructure.mappers.IRoleEntityMapper;
import com.pragma.hogar360_microservice_user.infraestructure.mappers.IUserEntityMapper;
import com.pragma.hogar360_microservice_user.infraestructure.repositories.mysql.IRoleRepository;
import com.pragma.hogar360_microservice_user.infraestructure.repositories.mysql.IUserRepository;
import com.pragma.hogar360_microservice_user.infraestructure.utils.jwt.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final IRoleEntityMapper roleEntityMapper;
    private final IRoleRepository roleRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IUserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public IAuthServicePort authServicePort(){
        return new AuthUseCase(authPersistencePort());
    }

    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(), rolePersistencePort(), encryptEncoderPort());
    }

    @Bean
    public IAuthPersistencePort authPersistencePort(){
        return new AuthPersistenceAdapter(userDetailsService, jwtUtils, encryptEncoderPort(), userEntityMapper);
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
    public IEncryptPersistencePort encryptEncoderPort() {
        return new EncryptPersistenceAdapter(passwordEncoder);
    }
}

