package com.pragma.hogar360_microservice_user.domain.usecases;

import com.pragma.hogar360_microservice_user.domain.exceptions.*;
import com.pragma.hogar360_microservice_user.domain.model.UserModel;
import com.pragma.hogar360_microservice_user.domain.ports.in.IUserServicePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IEncryptPersistencePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IRolePersistencePort;
import com.pragma.hogar360_microservice_user.domain.ports.out.IUserPersistencePort;

import static com.pragma.hogar360_microservice_user.domain.utils.constants.DomainConstants.ROLE_SELLER;
import static com.pragma.hogar360_microservice_user.domain.utils.validations.UserValidations.toUpperStringUserAttributes;
import static com.pragma.hogar360_microservice_user.domain.utils.validations.UserValidations.validationByUserAttributes;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolePersistencePort rolePersistencePort;
    private final IEncryptPersistencePort passwordEncoderPort;

    public UserUseCase(IUserPersistencePort userPersistencePort, IRolePersistencePort rolePersistencePort, IEncryptPersistencePort passwordEncoderPort) {
        this.userPersistencePort = userPersistencePort;
        this.rolePersistencePort = rolePersistencePort;
        this.passwordEncoderPort = passwordEncoderPort;
    }

    @Override
    public void saveSeller(UserModel userModel) {
        validationByUserAttributes(userModel);
        toUpperStringUserAttributes(userModel);

        verifyDataExistence(userModel);

        setDataUserModel(userModel, ROLE_SELLER);

        userPersistencePort.save(userModel);
    }

    private void verifyDataExistence(UserModel userModel){
        if (userPersistencePort.findByDocument(userModel.getDocument()).isPresent()){
            throw new DocumentAlreadyExistException();
        }

        if (userPersistencePort.findByEmail(userModel.getEmail()).isPresent()){
            throw new EmailAlreadyExistException();
        }
    }

    private void setDataUserModel(UserModel userModel, String nameRole){
        setRoleUserModel(userModel, nameRole);
        userModel.setPassword(encodePassword(userModel.getPassword()));
    }

    private void setRoleUserModel(UserModel userModel, String nameRole){
        userModel.setRoleModel(rolePersistencePort.findByName(nameRole).orElseThrow(RoleNotFoundException::new));
    }

    private String encodePassword(String password){
        return passwordEncoderPort.encode(password);
    }
}
