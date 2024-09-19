package com.emazon.api_user.domain.usecase;

import com.emazon.api_user.domain.api.IUserServicePort;
import com.emazon.api_user.domain.exception.*;
import com.emazon.api_user.domain.model.RolSave;
import com.emazon.api_user.domain.model.UserSave;
import com.emazon.api_user.domain.spi.IRolPersistencePort;
import com.emazon.api_user.domain.spi.IUserPersistencePort;
import com.emazon.api_user.domain.util.Constans;

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
    public void saveUser(UserSave userSave) {
        isIntegerDocument(userSave.getDocumentNumber());
        validateEmailStructure(userSave.getEmail());
        validPhoneStructure(userSave.getCellPhone());
        validationYear(userSave.getBirthdate());
        validatedEmailPresent(userSave.getEmail());
        userPersistencePort.saveUser(userSaveBuilder(userSave));
    }

    private void isIntegerDocument(String value) {
        if (!value.matches(Constans.REGEX_DOCUMENT)) {
            throw new DocumentInvalidException();
        }
    }

    private void validateEmailStructure(String email){
        Matcher matcher = Constans.EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new UserEmailInvalidException();
        }
    }

    private void validPhoneStructure(String phoneNumber) {
        Matcher matcher = Constans.PHONE_PATTERN.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new PhoneNumberinvalidException();
        }
    }

    private void validationYear(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        int age = Period.between(birthDate, today).getYears();

        if(age < Constans.VALUE_18){
            throw new MinorInvalidException();
        }
    }

    private void validatedEmailPresent(String email){
        if(this.userPersistencePort.getUserByEmail(email)) {
            throw new UserAlreadyExistsException();
        }
    }

    private UserSave userSaveBuilder(UserSave userSave){
        return UserSave.builder()
                .setName(userSave.getName())
                .setLastName(userSave.getLastName())
                .setDocumentNumber(userSave.getDocumentNumber())
                .setCellPhone(userSave.getCellPhone())
                .setBirthdate(userSave.getBirthdate())
                .setEmail(userSave.getEmail())
                .setPassword(this.userPersistencePort.encryptedPassword(userSave.getPassword()))
                .setRol(getRol())
                .build();
    }

    private RolSave getRol(){
        RolSave rolSave =  this.rolPersistencePort.getRolByName(Constans.AUX_BODEGA);
        if(rolSave == null){
            throw new RolAuxBodegaInvalidException();
        }
        return rolSave;
    }
}
