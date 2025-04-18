package com.pragma.hogar360_microservice_user.application.utils;

import static com.pragma.hogar360_microservice_user.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;

public class ApplicationConstants {

    private ApplicationConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static final String SAVE_SELLER_RESPONSE = "Seller saved successfully";
    public static final String LOG_IN_AUTHENTICATE_RESPONSE = "The user has successfully logged in";
}
