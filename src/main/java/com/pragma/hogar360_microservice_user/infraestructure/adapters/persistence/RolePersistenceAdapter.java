package com.pragma.hogar360_microservice_user.infraestructure.adapters.persistence;

import com.pragma.hogar360_microservice_user.domain.model.RoleModel;
import com.pragma.hogar360_microservice_user.domain.ports.out.IRolePersistencePort;
import com.pragma.hogar360_microservice_user.infraestructure.mappers.IRoleEntityMapper;
import com.pragma.hogar360_microservice_user.infraestructure.repositories.mysql.IRoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class RolePersistenceAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final IRoleEntityMapper roleEntityMapper;

    @Override
    public Optional<RoleModel> findByName(String name) {
        return roleEntityMapper.entityOptionalToModelOptional(roleRepository.findByName(name));
    }
}
