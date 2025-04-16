package com.pragma.hogar360_microservice_user.infraestructure.exceptionshandler;

import com.pragma.hogar360_microservice_user.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

import static com.pragma.hogar360_microservice_user.infraestructure.utils.constants.ExceptionConstants.*;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(DocumentAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> documentAlreadyExistException(DocumentAlreadyExistException exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        DOCUMENT_ALREADY_EXIST, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(DocumentNotAllowedException.class)
    public ResponseEntity<ExceptionResponse> documentNotAllowedException(DocumentNotAllowedException exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        DOCUMENT_NOT_ALLOWED, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> emailAlreadyExistException(EmailAlreadyExistException exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        EMAIL_ALREADY_EXIST, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(EmailNotAllowedException.class)
    public ResponseEntity<ExceptionResponse> emailNotAllowedException(EmailNotAllowedException exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        EMAIL_NOT_ALLOWED, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(MaxPhoneSizeExceedException.class)
    public ResponseEntity<ExceptionResponse> maxPhoneSizeExceedException(MaxPhoneSizeExceedException exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        MAX_PHONE_SIZE_EXCEEDED, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(NotOfLegalAgeException.class)
    public ResponseEntity<ExceptionResponse> notOfLegalAgeException(NotOfLegalAgeException exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        NOT_OF_LEGAL_AGE, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(PhoneNotAllowedException.class)
    public ResponseEntity<ExceptionResponse> phoneNotAllowedException(PhoneNotAllowedException exception){
        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        PHONE_NOT_ALLOWED, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ExceptionResponse> roleNotFoundException(RoleNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(ROLE_NOT_FOUND, LocalDateTime.now()
                )
        );
    }

    @ExceptionHandler(UserNameCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> userNameCannotBeEmptyException(UserNameCannotBeEmptyException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(NAME_CANNOT_BE_EMPTY, LocalDateTime.now()));
    }

    @ExceptionHandler(UserLastNameCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> userLastNameCannotBeEmptyException(UserLastNameCannotBeEmptyException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(LAST_NAME_CANNOT_BE_EMPTY, LocalDateTime.now()));
    }

    @ExceptionHandler(UserDocumentCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> userDocumentCannotBeEmptyException(UserDocumentCannotBeEmptyException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(ID_DOCUMENT_CANNOT_BE_EMPTY, LocalDateTime.now()));
    }

    @ExceptionHandler(UserPhoneNumberCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> userPhoneNumberCannotBeEmptyException(UserPhoneNumberCannotBeEmptyException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(PHONE_CANNOT_BE_EMPTY, LocalDateTime.now()));
    }

    @ExceptionHandler(UserEmailCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> userEmailCannotBeEmptyException(UserEmailCannotBeEmptyException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(EMAIL_CANNOT_BE_EMPTY, LocalDateTime.now()));
    }

    @ExceptionHandler(UserBirthdateCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> userBirthdateCannotBeEmptyException(UserBirthdateCannotBeEmptyException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(DATE_CANNOT_BE_EMPTY, LocalDateTime.now()));
    }

    @ExceptionHandler(UserPasswordCannotBeEmptyException.class)
    public ResponseEntity<ExceptionResponse> userPasswordCannotBeEmptyException(UserPasswordCannotBeEmptyException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(PASSWORD_CANNOT_BE_EMPTY, LocalDateTime.now()));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> handleForbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ExceptionResponse(NOT_PERMISSIONS_MESSAGE, LocalDateTime.now()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> badCredentialsException(BadCredentialsException exception){
        return ResponseEntity.badRequest().body(new ExceptionResponse(exception.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionResponse> userNotFoundException(UserNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse(USER_NOT_FOUND_MESSAGE, LocalDateTime.now()
                )
        );
    }
}
