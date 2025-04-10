package com.pragma.hogar360_microservice_user.domain.utils.constants;

public class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }
    public static final String ROLE_SELLER = "SELLER";

    public static final String VALIDATIONS_STR_FROM_NULL_TO_BLANK = "";
    public static final String VALIDATIONS_STR_REGEX = "\\p{M}";
    public static final String VALIDATIONS_STR_REGEX_TO_BLANK = "";

    public static final String UTILITY_CLASS_MESSAGE = "Utility class";
}
