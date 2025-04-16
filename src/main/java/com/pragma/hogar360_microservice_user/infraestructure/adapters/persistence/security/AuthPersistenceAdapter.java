package com.pragma.hogar360_microservice_user.infraestructure.adapters.persistence.security;

import com.pragma.hogar360_microservice_user.domain.model.UserModel;
import com.pragma.hogar360_microservice_user.domain.ports.out.IAuthPersistencePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IEncryptPersistencePort;
import com.pragma.hogar360_microservice_user.infraestructure.entities.UserEntity;
import com.pragma.hogar360_microservice_user.infraestructure.mappers.IUserEntityMapper;
import com.pragma.hogar360_microservice_user.infraestructure.utils.jwt.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import static com.pragma.hogar360_microservice_user.infraestructure.exceptionshandler.ExceptionConstants.INCORRECT_PASSWORD_MESSAGE;


@Service
@Transactional
@RequiredArgsConstructor
public class AuthPersistenceAdapter implements IAuthPersistencePort {

    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final IEncryptPersistencePort encryptPersistencePort;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public UserModel authenticate(String email, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        if (!encryptPersistencePort.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException(INCORRECT_PASSWORD_MESSAGE);
        }

        return userEntityMapper.entityToModel((UserEntity) userDetails);
    }

    @Override
    public String generateToken(UserModel userModel) {
        return jwtUtils.generateToken(userModel);
    }
}
