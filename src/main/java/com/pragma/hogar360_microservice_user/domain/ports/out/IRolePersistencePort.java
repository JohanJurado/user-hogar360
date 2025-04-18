package com.pragma.hogar360_microservice_user.domain.ports.out;

import com.pragma.hogar360_microservice_user.domain.model.RoleModel;

import java.util.Optional;

public interface IRolePersistencePort {

    Optional<RoleModel> findByName(String name);
}
