package com.pragma.hogar360_microservice_user.infraestructure.mappers;

import com.pragma.hogar360_microservice_user.domain.model.RoleModel;
import com.pragma.hogar360_microservice_user.infraestructure.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IRoleEntityMapper {

    RoleModel entityToModel(RoleEntity roleEntity);

    default Optional<RoleModel> entityOptionalToModelOptional(Optional<RoleEntity> roleEntityOptional){
        return roleEntityOptional.map(this::entityToModel);
    }
}
