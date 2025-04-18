package com.pragma.hogar360_microservice_user.infraestructure.repositories.mysql;


import com.pragma.hogar360_microservice_user.infraestructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByDocument(String document);
    Optional<UserEntity> findByEmail(String email);
}
