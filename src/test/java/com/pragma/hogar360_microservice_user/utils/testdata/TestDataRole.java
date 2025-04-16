package com.pragma.hogar360_microservice_user.utils.testdata;

import com.pragma.hogar360_microservice_user.domain.model.RoleModel;

public class TestDataRole {

    public static RoleModel getRoleModelSeller(){
        RoleModel roleModel = new RoleModel();
        roleModel.setId(1L);
        roleModel.setName("SELLER");
        roleModel.setDescription("DESCRIPTION SELLER");
        return roleModel;
    }
}
