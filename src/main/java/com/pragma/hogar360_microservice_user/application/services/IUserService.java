package com.pragma.hogar360_microservice_user.application.services;

import com.pragma.hogar360_microservice_user.application.dtos.request.UserRequest;
import com.pragma.hogar360_microservice_user.application.dtos.response.SaveUserResponse;

public interface IUserService {

    SaveUserResponse saveSeller(UserRequest userRequest);
}
