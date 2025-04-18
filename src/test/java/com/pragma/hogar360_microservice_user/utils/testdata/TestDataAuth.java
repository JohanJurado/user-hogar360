package com.pragma.hogar360_microservice_user.utils.testdata;

import com.pragma.hogar360_microservice_user.domain.model.RoleModel;
import com.pragma.hogar360_microservice_user.domain.model.UserModel;

import static com.pragma.hogar360_microservice_user.utils.constants.AuthTestConstants.*;

public class TestDataAuth {
    public static UserModel getValidUser() {
        UserModel user = new UserModel();
        user.setId(USER_ID);
        user.setName(USER_NAME);
        user.setLastName(USER_LASTNAME);
        user.setEmail(VALID_EMAIL);
        user.setPassword(VALID_PASSWORD);
        user.setBirthdate(USER_BIRTHDATE);
        user.setDocument(USER_DOCUMENT);
        user.setPhoneNumber(USER_PHONE);
        user.setRoleModel(new RoleModel());
        return user;
    }

    public static UserModel getUserWithInvalidEmail() {
        UserModel user = getValidUser();
        user.setEmail(INVALID_EMAIL_FORMAT);
        return user;
    }

    public static UserModel getUserWithEmptyEmail() {
        UserModel user = getValidUser();
        user.setEmail(EMPTY_EMAIL);
        return user;
    }

    public static UserModel getUserWithEmptyPassword() {
        UserModel user = getValidUser();
        user.setPassword(EMPTY_PASSWORD);
        return user;
    }
}
