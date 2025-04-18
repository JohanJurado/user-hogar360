package com.pragma.hogar360_microservice_user.application.mappers;

import com.pragma.hogar360_microservice_user.application.dtos.request.UserRequest;
import com.pragma.hogar360_microservice_user.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserDtoMapper {

    UserModel requestToModel(UserRequest userRequest);
}
