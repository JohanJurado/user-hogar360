package com.pragma.hogar360_microservice_user.utils;

import com.pragma.hogar360_microservice_user.domain.model.RoleModel;

public class TestDataRole {

    public static RoleModel getRoleModelSeller(){
        return new RoleModel(1L, "SELLER", "DESCRIPTION SELLER");
    }
}
