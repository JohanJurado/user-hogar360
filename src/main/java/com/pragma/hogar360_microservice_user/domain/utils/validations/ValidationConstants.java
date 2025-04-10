package com.pragma.hogar360_microservice_user.domain.utils.validations;

import static com.pragma.hogar360_microservice_user.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;

public class ValidationConstants {

    private ValidationConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static final Long MAX_PHONE_EXCEED_MESSAGE = 13L;

    public static final String REGEX_VALID_PHONE_NUMBER_FORMAT = "^\\+?\\d{1,13}$";
    public static final String REGEX_VALID_EMAIL_FORMAT = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,4}$";
    public static final String REGEX_VALID_DOCUMENT_FORMAT = "\\d+";

    public static final int LEGAL_AGE = 18;
}
