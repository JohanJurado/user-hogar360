package com.pragma.hogar360_microservice_user.domain.utils.validations;

import com.pragma.hogar360_microservice_user.domain.exceptions.*;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.regex.Pattern;

import static com.pragma.hogar360_microservice_user.domain.utils.constants.DomainConstants.*;
import static com.pragma.hogar360_microservice_user.domain.utils.constants.ValidationConstants.*;

public class GlobalValidations {

    private GlobalValidations() {
        throw new IllegalStateException(UTILITY_CLASS_MESSAGE);
    }

    public static void validationByPhoneNumber(String phoneNumber){
        if (phoneNumber.length() > MAX_PHONE_EXCEED_MESSAGE){
            throw new MaxPhoneSizeExceedException();
        }

        Pattern pattern = Pattern.compile(REGEX_VALID_PHONE_NUMBER_FORMAT);
        if (!pattern.matcher(phoneNumber).matches()){
            throw new PhoneNotAllowedException();
        }
    }

    public static void validationByEmailFormat(String email){
        Pattern pattern = Pattern.compile(REGEX_VALID_EMAIL_FORMAT);
        if (!pattern.matcher(email).matches()){
            throw new EmailNotAllowedException();
        }
    }

    public static void validationByLegalAge(LocalDate birthdate){
        if (birthdate == null) {
            throw new UserBirthdateCannotBeEmptyException();
        }

        if (Period.between(birthdate, LocalDate.now()).getYears() < LEGAL_AGE){
            throw new NotOfLegalAgeException();
        }
    }

    public static void validationByDocument(String document){
        if (!document.matches(REGEX_VALID_DOCUMENT_FORMAT)){
            throw new DocumentNotAllowedException();
        }
    }

    public static void validationByAttributeIsNullOrBlank(String attribute, RuntimeException ex){
        attribute = Objects.requireNonNullElse(attribute, VALIDATIONS_STR_FROM_NULL_TO_BLANK);
        if (attribute.isBlank()){
            throw ex;
        }
    }

    public static String normalizeToUpper(String attribute) {
        String normalized = Normalizer.normalize(attribute, Normalizer.Form.NFD);
        normalized = normalized.replaceAll(VALIDATIONS_STR_REGEX, VALIDATIONS_STR_REGEX_TO_BLANK);
        return normalized.toUpperCase();
    }
}
