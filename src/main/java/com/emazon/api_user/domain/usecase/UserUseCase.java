package com.emazon.api_user.domain.usecase;

import com.emazon.api_user.domain.api.IUserServicePort;
import com.emazon.api_user.domain.exception.*;
import com.emazon.api_user.domain.model.RolSave;
import com.emazon.api_user.domain.model.UserSave;
import com.emazon.api_user.domain.spi.IRolPersistencePort;
import com.emazon.api_user.domain.spi.IUserPersistencePort;
import com.emazon.api_user.domain.util.ConstantsUseCase;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IRolPersistencePort rolPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort,
                       IRolPersistencePort rolPersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.rolPersistencePort = rolPersistencePort;
    }

    @Override
    public void saveUser(UserSave userSave, String role) {
        isIntegerDocument(userSave.getDocumentNumber());
        validateEmailStructure(userSave.getEmail());
        validPhoneStructure(userSave.getCellPhone());
        validationYear(userSave.getBirthdate());
        validatedEmailPresent(userSave.getEmail());
        userPersistencePort.saveUser(userSaveBuilder(userSave,role));
    }

    private void isIntegerDocument(String value) {
        if (!value.matches(ConstantsUseCase.REGEX_DOCUMENT)) {
            throw new DocumentInvalidException();
        }
    }

    private void validateEmailStructure(String email){
        Matcher matcher = ConstantsUseCase.EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new UserEmailInvalidException();
        }
    }

    private void validPhoneStructure(String phoneNumber) {
        Matcher matcher = ConstantsUseCase.PHONE_PATTERN.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new PhoneNumberinvalidException();
        }
    }

    private void validationYear(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();

        if(age < ConstantsUseCase.ADULT){
            throw new MinorInvalidException();
        }
    }

    private void validatedEmailPresent(String email){
        if(this.userPersistencePort.getUserByEmail(email)) {
            throw new UserAlreadyExistsException();
        }
    }

    private UserSave userSaveBuilder(UserSave userSave,String role){
        return UserSave.builder()
                .setName(userSave.getName())
                .setLastName(userSave.getLastName())
                .setDocumentNumber(userSave.getDocumentNumber())
                .setCellPhone(userSave.getCellPhone())
                .setBirthdate(userSave.getBirthdate())
                .setEmail(userSave.getEmail())
                .setPassword(this.userPersistencePort.encryptedPassword(userSave.getPassword()))
                .setRol(setRol(role))
                .build();
    }

    private RolSave setRol(String role){
        RolSave rolSave =  this.rolPersistencePort.getRolByName(getRol(role));
        if(rolSave == null){
            throw new RolAuxBodegaInvalidException();
        }
        return rolSave;
    }

    String getRol(String role) {
        if(role.equals(ConstantsUseCase.ROLE_AUX_WAREHOUSE)){
            return ConstantsUseCase.ROLE_AUX_WAREHOUSE;
        }
        return ConstantsUseCase.ROLE_CLIENT;
    }
}
