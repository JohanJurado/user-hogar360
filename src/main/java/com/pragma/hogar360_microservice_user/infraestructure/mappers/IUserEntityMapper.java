package com.pragma.hogar360_microservice_user.infraestructure.mappers;

import com.pragma.hogar360_microservice_user.domain.model.UserModel;
import com.pragma.hogar360_microservice_user.infraestructure.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserEntityMapper {

    @Mapping(target = "roleModel", source="roleEntity")
    UserModel entityToModel(UserEntity userEntity);

    default Optional<UserModel> entityOptionalToModelOptional(Optional<UserEntity> userEntityOptional){
        return userEntityOptional.map(this::entityToModel);
    }

    @Mapping(target = "roleEntity", source="roleModel")
    UserEntity modelToEntity(UserModel userModel);
}
