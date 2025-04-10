package com.pragma.hogar360_microservice_user.application.services.impl;

import com.pragma.hogar360_microservice_user.application.dtos.request.UserRequest;
import com.pragma.hogar360_microservice_user.application.dtos.response.SaveUserResponse;
import com.pragma.hogar360_microservice_user.application.mappers.IUserDtoMapper;
import com.pragma.hogar360_microservice_user.application.services.IUserService;
import com.pragma.hogar360_microservice_user.domain.ports.in.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.pragma.hogar360_microservice_user.application.utils.ApplicationConstants.SAVE_SELLER_RESPONSE;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserServicePort userServicePort;
    private final IUserDtoMapper userDtoMapper;

    @Override
    public SaveUserResponse saveSeller(UserRequest userRequest) {
        userServicePort.saveSeller(userDtoMapper.requestToModel(userRequest));
        return new SaveUserResponse(SAVE_SELLER_RESPONSE, LocalDateTime.now());
    }
}
