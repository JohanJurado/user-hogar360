package com.pragma.hogar360_microservice_user.infraestructure.adapters.security;

import com.pragma.hogar360_microservice_user.domain.exceptions.UserNotFoundException;
import com.pragma.hogar360_microservice_user.infraestructure.repositories.mysql.IUserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserDetailsPersistenceAdapter implements UserDetailsService {

    private final IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
    }

}
