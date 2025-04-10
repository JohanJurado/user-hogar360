package com.pragma.hogar360_microservice_user.infraestructure.repositories.mysql;

import com.pragma.hogar360_microservice_user.infraestructure.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RoleEntity, Long> {

    Optional<RoleEntity> findByName(String name);
}
