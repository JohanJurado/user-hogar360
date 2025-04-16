package com.pragma.hogar360_microservice_user.domain.usecases;

import com.pragma.hogar360_microservice_user.domain.exceptions.*;
import com.pragma.hogar360_microservice_user.domain.model.RoleModel;
import com.pragma.hogar360_microservice_user.domain.model.UserModel;
import com.pragma.hogar360_microservice_user.domain.ports.out.IEncryptPersistencePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IRolePersistencePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IUserPersistencePort;
import com.pragma.hogar360_microservice_user.domain.utils.constants.DomainConstants;
import com.pragma.hogar360_microservice_user.domain.utils.validations.ValidationConstants;
import com.pragma.hogar360_microservice_user.domain.utils.validations.GlobalValidations;
import com.pragma.hogar360_microservice_user.utils.constants.TestConstants;
import com.pragma.hogar360_microservice_user.utils.testdata.TestDataRole;
import com.pragma.hogar360_microservice_user.utils.testdata.TestDataUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Mock
    private IUserPersistencePort userPersistencePort;
    @Mock
    private IRolePersistencePort rolePersistencePort;
    @Mock
    private IEncryptPersistencePort passwordEncoderPort;
    @InjectMocks
    private UserUseCase userUseCase;

    @Test
    @DisplayName("Check Save Seller Successfully")
    void checkSaveSellerSuccessfully() {
        UserModel userModel = TestDataUser.getUserModel();
        RoleModel roleSeller = TestDataRole.getRoleModelSeller();

        when(passwordEncoderPort.encode(userModel.getPassword())).thenReturn(userModel.getPassword());
        when(rolePersistencePort.findByName(roleSeller.getName().toUpperCase())).thenReturn(Optional.of(roleSeller));
        when(userPersistencePort.findByDocument(userModel.getDocument())).thenReturn(Optional.empty());
        when(userPersistencePort.findByEmail(userModel.getEmail().toUpperCase())).thenReturn(Optional.empty());

        userUseCase.saveSeller(userModel);

        verify(rolePersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findByName(roleSeller.getName().toUpperCase());
        verify(userPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findByDocument(userModel.getDocument());
        verify(userPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findByEmail(userModel.getEmail().toUpperCase());
        verify(userPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).save(userModel);
    }

    @Test
    @DisplayName("Show DocumentAlreadyExistException")
    void showDocumentAlreadyExistException() {
        UserModel userModel = TestDataUser.getUserModel();

        when(userPersistencePort.findByDocument(userModel.getDocument())).thenReturn(Optional.of(userModel));

        assertThrows(
                DocumentAlreadyExistException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(userPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findByDocument(userModel.getDocument());
        verify(userPersistencePort, never()).findByEmail(anyString());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show EmailAlreadyExistException")
    void showEmailAlreadyExistException() {
        UserModel userModel = TestDataUser.getUserModel();

        when(userPersistencePort.findByDocument(userModel.getDocument())).thenReturn(Optional.empty());
        when(userPersistencePort.findByEmail(userModel.getEmail().toUpperCase())).thenReturn(Optional.of(userModel));

        assertThrows(
                EmailAlreadyExistException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(userPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findByDocument(userModel.getDocument());
        verify(userPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findByEmail(userModel.getEmail().toUpperCase());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show RoleNotFoundException")
    void showRoleNotFoundException() {
        UserModel userModel = TestDataUser.getUserModel();
        RoleModel roleSeller = TestDataRole.getRoleModelSeller();

        when(userPersistencePort.findByDocument(userModel.getDocument())).thenReturn(Optional.empty());
        when(userPersistencePort.findByEmail(userModel.getEmail().toUpperCase())).thenReturn(Optional.empty());
        when(rolePersistencePort.findByName(roleSeller.getName())).thenReturn(Optional.empty());

        assertThrows(
                RoleNotFoundException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(userPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findByDocument(userModel.getDocument());
        verify(userPersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findByEmail(userModel.getEmail().toUpperCase());
        verify(rolePersistencePort, times(TestConstants.VERIFY_ONE_INVOCATIONS)).findByName(roleSeller.getName());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show DocumentNotAllowedException")
    void showDocumentNotAllowedException() {
        UserModel userModel = TestDataUser.getUserDocumentNotAllowed();

        assertThrows(
                DocumentNotAllowedException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(rolePersistencePort, never()).findByName(anyString());
        verify(userPersistencePort, never()).findByDocument(anyString());
        verify(userPersistencePort, never()).findByEmail(anyString());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show EmailNotAllowedException")
    void showEmailNotAllowedException() {
        UserModel userModel = TestDataUser.getUserEmailNotAllowed();

        assertThrows(
                EmailNotAllowedException.class,
                () -> userUseCase.saveSeller(userModel)

        );

        verify(rolePersistencePort, never()).findByName(anyString());
        verify(userPersistencePort, never()).findByDocument(anyString());
        verify(userPersistencePort, never()).findByEmail(anyString());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show PhoneNotAllowedException")
    void showPhoneNotAllowedException() {
        UserModel userModel = TestDataUser.getUserPhoneNumberNotAllowed();

        assertThrows(
                PhoneNotAllowedException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(rolePersistencePort, never()).findByName(anyString());
        verify(userPersistencePort, never()).findByDocument(anyString());
        verify(userPersistencePort, never()).findByEmail(anyString());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show MaxPhoneSizeExceedException")
    void showMaxPhoneSizeExceedException() {
        UserModel userModel = TestDataUser.getUserPhoneNumberMaxSize();

        assertThrows(
                MaxPhoneSizeExceedException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(rolePersistencePort, never()).findByName(anyString());
        verify(userPersistencePort, never()).findByDocument(anyString());
        verify(userPersistencePort, never()).findByEmail(anyString());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show NotOfLegalAgeException")
    void showNotOfLegalAgeException() {
        UserModel userModel = TestDataUser.getUserNotOfLegalAge();

        assertThrows(
                NotOfLegalAgeException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(rolePersistencePort, never()).findByName(anyString());
        verify(userPersistencePort, never()).findByDocument(anyString());
        verify(userPersistencePort, never()).findByEmail(anyString());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show UserNameCannotBeEmptyException When Name User Is Empty")
    void showUserNameCannotBeEmptyExceptionWhenNameUserIsEmpty() {
        UserModel userModel = TestDataUser.getUserNameBlank();

        assertThrows(
                UserNameCannotBeEmptyException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(rolePersistencePort, never()).findByName(anyString());
        verify(userPersistencePort, never()).findByDocument(anyString());
        verify(userPersistencePort, never()).findByEmail(anyString());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show UserLastNameCannotBeEmptyException When Last Name User Is Empty")
    void showUserLastNameCannotBeEmptyExceptionWhenLastNameUserIsEmpty() {
        UserModel userModel = TestDataUser.getUserLastNameBlank();

        assertThrows(
                UserLastNameCannotBeEmptyException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(rolePersistencePort, never()).findByName(anyString());
        verify(userPersistencePort, never()).findByDocument(anyString());
        verify(userPersistencePort, never()).findByEmail(anyString());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show UserDocumentCannotBeEmptyException When Document User Is Empty")
    void showUserDocumentCannotBeEmptyExceptionWhenDocumentUserIsEmpty() {
        UserModel userModel = TestDataUser.getUserDocumentBlank();

        assertThrows(
                UserDocumentCannotBeEmptyException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(rolePersistencePort, never()).findByName(anyString());
        verify(userPersistencePort, never()).findByDocument(anyString());
        verify(userPersistencePort, never()).findByEmail(anyString());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show UserEmailCannotBeEmptyException When Email User Is Empty")
    void showUserEmailCannotBeEmptyExceptionWhenEmailUserIsEmpty() {
        UserModel userModel = TestDataUser.getUserEmailBlank();

        assertThrows(
                UserEmailCannotBeEmptyException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(rolePersistencePort, never()).findByName(anyString());
        verify(userPersistencePort, never()).findByDocument(anyString());
        verify(userPersistencePort, never()).findByEmail(anyString());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show UserBirthdateCannotBeEmptyException When Birthdate User Is Empty")
    void showUserBirthdateCannotBeEmptyExceptionWhenBirthDateUserIsEmpty() {
        UserModel userModel = TestDataUser.getUserBirthdateNull();

        assertThrows(
                UserBirthdateCannotBeEmptyException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(rolePersistencePort, never()).findByName(anyString());
        verify(userPersistencePort, never()).findByDocument(anyString());
        verify(userPersistencePort, never()).findByEmail(anyString());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Show UserPasswordCannotBeEmptyException When Password User Is Empty")
    void showUserPasswordCannotBeEmptyExceptionWhenPasswordUserIsEmpty() {
        UserModel userModel = TestDataUser.getUserPasswordBlank();

        assertThrows(
                UserPasswordCannotBeEmptyException.class,
                () -> userUseCase.saveSeller(userModel)
        );

        verify(rolePersistencePort, never()).findByName(anyString());
        verify(userPersistencePort, never()).findByDocument(anyString());
        verify(userPersistencePort, never()).findByEmail(anyString());
        verify(userPersistencePort, never()).save(any(UserModel.class));
    }

    @Test
    @DisplayName("Test Validation Constructor ThrowsIllegalStateException")
    void testValidationConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<GlobalValidations> constructor = GlobalValidations.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(DomainConstants.UTILITY_CLASS_MESSAGE, cause.getMessage());
    }

    @Test
    @DisplayName("Test ValidationConstants Constructor ThrowsIllegalStateException")
    void testValidationConstantsConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<ValidationConstants> constructor = ValidationConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(DomainConstants.UTILITY_CLASS_MESSAGE, cause.getMessage());
    }

    @Test
    @DisplayName("Test DomainConstants Constructor ThrowsIllegalStateException")
    void testDomainConstantsConstructorThrowsIllegalStateException() {
        Exception exception = assertThrows(InvocationTargetException.class, () -> {
            Constructor<DomainConstants> constructor = DomainConstants.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        });

        Throwable cause = exception.getCause();
        assertNotNull(cause);
        assertEquals(IllegalStateException.class, cause.getClass());

        assertEquals(DomainConstants.UTILITY_CLASS_MESSAGE, cause.getMessage());
    }
}