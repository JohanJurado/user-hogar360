package com.pragma.hogar360_microservice_user.infraestructure.utils.constants;

import static com.pragma.hogar360_microservice_user.domain.utils.constants.DomainConstants.UTILITY_CLASS_MESSAGE;

public class ExceptionConstants {

    private ExceptionConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    // specific validations user
    public static final String MAX_PHONE_SIZE_EXCEEDED = "The phone of user cannot exceed 13 characters";
    public static final String EMAIL_ALREADY_EXIST = "The email of user already exist";
    public static final String DOCUMENT_ALREADY_EXIST = "The document of user already exist";
    public static final String PHONE_NOT_ALLOWED = "The phone format entered is not allowed";
    public static final String DOCUMENT_NOT_ALLOWED = "The document format entered is not allowed";
    public static final String ROLE_NOT_FOUND = "Rol not found";
    public static final String EMAIL_NOT_ALLOWED = "The email format entered is not allowed";
    public static final String NOT_OF_LEGAL_AGE = "The date of user is less than 18";

    // empty validation user
    public static final String NAME_CANNOT_BE_EMPTY = "The field 'name' cannot be empty or blank";
    public static final String LAST_NAME_CANNOT_BE_EMPTY = "The field 'last name' cannot be empty or blank";
    public static final String DATE_CANNOT_BE_EMPTY = "The field 'birthdate' cannot be empty or blank";
    public static final String EMAIL_CANNOT_BE_EMPTY = "The field 'email' cannot be empty or blank";
    public static final String ID_DOCUMENT_CANNOT_BE_EMPTY = "The field 'document' cannot be empty or blank";
    public static final String PHONE_CANNOT_BE_EMPTY = "The field 'phone number' cannot be empty or blank";
    public static final String PASSWORD_CANNOT_BE_EMPTY = "The field 'password' cannot be empty or blank";

    // token validation
    public static final String TOKEN_ALGORITHM_INVALID_MESSAGE = "Token algorithm is invalid";
    public static final String TOKEN_SIGNATURE_INVALID_MESSAGE = "Token signature is invalid";
    public static final String TOKEN_EXPIRED_MESSAGE = "Token has expired";
    public static final String TOKEN_ISSUER_INVALID_MESSAGE = "Token issuer is invalid";
    public static final String TOKEN_MALFORMED_MESSAGE = "Token is malformed";
    public static final String INVALID_KEY_OR_TOKEN_MESSAGE = "Invalid key or token";
    public static final String TOKEN_VALIDATION_FAILED_MESSAGE = "Token validation failed";

    // validate credentials
    public static final String USER_NOT_FOUND_MESSAGE = "User not found";
    public static final String INCORRECT_PASSWORD_MESSAGE = "Incorrect Password";
    public static final String NOT_PERMISSIONS_MESSAGE = "You don't have permissions to perform this action";

    // format exceptions of token
    public static final String CONTENT_TYPE = "application/json";
    public static final String FORMAT_MESSAGE_EXCEPTION = "{\"error\": \"%s\", \"status\": %d}";

}
