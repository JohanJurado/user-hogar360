package com.pragma.hogar360_microservice_user.domain.ports.in;

import com.pragma.hogar360_microservice_user.domain.model.UserModel;

public interface IUserServicePort {

    void saveSeller(UserModel userModel);
}
