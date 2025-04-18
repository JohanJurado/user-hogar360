package com.pragma.hogar360_microservice_user.domain.usecases;

import com.pragma.hogar360_microservice_user.domain.exceptions.*;
import com.pragma.hogar360_microservice_user.domain.model.UserModel;
import com.pragma.hogar360_microservice_user.domain.ports.out.IAuthPersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.pragma.hogar360_microservice_user.utils.constants.AuthTestConstants.*;
import static com.pragma.hogar360_microservice_user.utils.testdata.TestDataAuth.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthUseCaseTest {

    @Mock
    private IAuthPersistencePort authPersistencePort;

    @InjectMocks
    private AuthUseCase authUseCase;

    @Test
    void login_WithValidCredentials_ShouldReturnToken() {
        UserModel mockUser = getValidUser();
        when(authPersistencePort.authenticate(NORMALIZED_EMAIL, VALID_PASSWORD))
                .thenReturn(mockUser);
        when(authPersistencePort.generateToken(mockUser)).thenReturn(MOCK_TOKEN);

        String token = authUseCase.login(VALID_EMAIL, VALID_PASSWORD);

        assertEquals(MOCK_TOKEN, token);
        verify(authPersistencePort).authenticate(NORMALIZED_EMAIL, VALID_PASSWORD);
        verify(authPersistencePort).generateToken(mockUser);
    }

    @Test
    void login_WithEmptyEmail_ShouldThrowException() {
        assertThrows(UserEmailCannotBeEmptyException.class,
                () -> authUseCase.login(EMPTY_EMAIL, VALID_PASSWORD));
    }

    @Test
    void login_WithEmptyPassword_ShouldThrowException() {
        assertThrows(UserPasswordCannotBeEmptyException.class,
                () -> authUseCase.login(VALID_EMAIL, EMPTY_PASSWORD));
    }

    @Test
    void login_WithInvalidEmailFormat_ShouldThrowException() {
        assertThrows(EmailNotAllowedException.class,
                () -> authUseCase.login(INVALID_EMAIL_FORMAT, VALID_PASSWORD));
    }

    @Test
    void login_WithNullEmail_ShouldThrowException() {
        assertThrows(UserEmailCannotBeEmptyException.class,
                () -> authUseCase.login(NULL_STRING, VALID_PASSWORD));
    }

    @Test
    void login_WithNullPassword_ShouldThrowException() {
        assertThrows(UserPasswordCannotBeEmptyException.class,
                () -> authUseCase.login(VALID_EMAIL, NULL_STRING));
    }

    @Test
    void login_ShouldNormalizeEmailToUpperCase() {
        UserModel mockUser = getValidUser();
        when(authPersistencePort.authenticate(NORMALIZED_EMAIL, VALID_PASSWORD))
                .thenReturn(mockUser);
        when(authPersistencePort.generateToken(mockUser)).thenReturn(MOCK_TOKEN);

        String token = authUseCase.login(NOT_NORMALIZED_EMAIL, VALID_PASSWORD);

        verify(authPersistencePort).authenticate(NORMALIZED_EMAIL, VALID_PASSWORD);
        assertEquals(MOCK_TOKEN, token);
    }
}