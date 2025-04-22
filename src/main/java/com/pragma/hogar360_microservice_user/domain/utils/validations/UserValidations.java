package com.pragma.hogar360_microservice_user.domain.utils.validations;

import com.pragma.hogar360_microservice_user.domain.exceptions.*;
import com.pragma.hogar360_microservice_user.domain.model.UserModel;

import static com.pragma.hogar360_microservice_user.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;
import static com.pragma.hogar360_microservice_user.domain.utils.validations.GlobalValidations.*;

public class UserValidations {

    private UserValidations() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }


    public static void toUpperStringUserAttributes(UserModel userModel) {
        userModel.setName(normalizeToUpper(userModel.getName()));
        userModel.setLastName(normalizeToUpper(userModel.getLastName()));
        userModel.setEmail(normalizeToUpper(userModel.getEmail()));
    }

    public static void validationByUserAttributes(UserModel userModel) {
        validationByAttributeIsNullOrBlank(userModel.getName(), new UserNameCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(userModel.getLastName(), new UserLastNameCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(userModel.getDocument(), new UserDocumentCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(userModel.getPhoneNumber(), new UserPhoneNumberCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(userModel.getEmail(), new UserEmailCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(userModel.getPassword(), new UserPasswordCannotBeEmptyException());

        validationByPhoneNumber(userModel.getPhoneNumber());
        validationByDocument(userModel.getDocument());
        validationByLegalAge(userModel.getBirthdate());
        validationByEmailFormat(userModel.getEmail());
    }

    public static void validationByAuthAttributes(String email, String password){
        validationByAttributeIsNullOrBlank(email, new UserEmailCannotBeEmptyException());
        validationByAttributeIsNullOrBlank(password, new UserPasswordCannotBeEmptyException());

        validationByEmailFormat(email);
    }
}
