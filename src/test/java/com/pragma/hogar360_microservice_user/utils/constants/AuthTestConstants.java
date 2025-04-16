package com.pragma.hogar360_microservice_user.utils.constants;

import java.time.LocalDate;

public class AuthTestConstants {

    public static final String VALID_EMAIL = "user@example.com";
    public static final String VALID_PASSWORD = "SecurePass123!";
    public static final String NORMALIZED_EMAIL = "USER@EXAMPLE.COM";
    public static final String NOT_NORMALIZED_EMAIL = "UsEr@ExAmPlE.com";

    public static final String INVALID_EMAIL_FORMAT = "invalid-email";
    public static final String EMPTY_EMAIL = "";
    public static final String EMPTY_PASSWORD = "";
    public static final String NULL_STRING = null;

    public static final String MOCK_TOKEN = "mock.jwt.token";

    public static final Long USER_ID = 1L;
    public static final String USER_NAME = "John";
    public static final String USER_LASTNAME = "Doe";
    public static final LocalDate USER_BIRTHDATE = LocalDate.now().minusYears(20);
    public static final String USER_DOCUMENT = "123456789";
    public static final String USER_PHONE = "+573001234567";
}
