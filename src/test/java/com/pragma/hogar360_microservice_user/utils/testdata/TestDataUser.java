package com.pragma.hogar360_microservice_user.utils.testdata;

import com.pragma.hogar360_microservice_user.domain.model.UserModel;

import java.time.LocalDate;

public class TestDataUser {

    public static UserModel getUserModel(){
        UserModel userModel = new UserModel();
        userModel.setId(1L);
        userModel.setName("User 1");
        userModel.setLastName("Last name user 1");
        userModel.setDocument("12345678");
        userModel.setPhoneNumber("3162345387");
        userModel.setEmail("email@email.com");
        userModel.setBirthdate(LocalDate.parse("2000-01-01"));
        userModel.setPassword("12345");
        return userModel;
    }

    public static UserModel getUserBirthdateNull(){
        UserModel userModel = getUserModel();
        userModel.setBirthdate(null);
        return userModel;
    }

    public static UserModel getUserNameBlank(){
        UserModel userModel = getUserModel();
        userModel.setName("");
        return userModel;
    }

    public static UserModel getUserLastNameBlank(){
        UserModel userModel = getUserModel();
        userModel.setLastName("");
        return userModel;
    }

    public static UserModel getUserDocumentBlank(){
        UserModel userModel = getUserModel();
        userModel.setDocument("");
        return userModel;
    }

    public static UserModel getUserEmailBlank(){
        UserModel userModel = getUserModel();
        userModel.setEmail("");
        return userModel;
    }

    public static UserModel getUserPasswordBlank(){
        UserModel userModel = getUserModel();
        userModel.setPassword("");
        return userModel;
    }

    public static UserModel getUserDocumentNotAllowed(){
        UserModel userModel = getUserModel();
        userModel.setDocument("12345678A");
        return userModel;
    }

    public static UserModel getUserEmailNotAllowed(){
        UserModel userModel = getUserModel();
        userModel.setEmail("emailemail.com");
        return userModel;
    }

    public static UserModel getUserPhoneNumberMaxSize(){
        UserModel userModel = getUserModel();
        userModel.setPhoneNumber("1".repeat(14));
        return userModel;
    }

    public static UserModel getUserPhoneNumberNotAllowed(){
        UserModel userModel = getUserModel();
        userModel.setPhoneNumber("-573184534276");
        return userModel;
    }

    public static UserModel getUserNotOfLegalAge(){
        UserModel userModel = getUserModel();
        userModel.setBirthdate(LocalDate.parse("2025-01-01"));
        return userModel;
    }
}
