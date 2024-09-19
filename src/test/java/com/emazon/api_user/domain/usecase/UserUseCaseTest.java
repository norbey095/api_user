package com.emazon.api_user.domain.usecase;

import com.emazon.api_user.domain.exception.*;
import com.emazon.api_user.domain.model.RolSave;
import com.emazon.api_user.domain.model.UserSave;
import com.emazon.api_user.domain.spi.IRolPersistencePort;
import com.emazon.api_user.domain.spi.IUserPersistencePort;
import com.emazon.api_user.domain.util.Constans;
import com.emazon.api_user.domain.util.ConstantsDomain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRolPersistencePort rolPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallSaveUserInPersistencePort() {
        RolSave rolSave = new RolSave(ConstantsDomain.ROL_ID, ConstantsDomain.ROL_NAME
                , ConstantsDomain.ROL_DESCRIPTION);
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomain.NAME)
                .setLastName(ConstantsDomain.LAST_NAME)
                .setDocumentNumber(ConstantsDomain.DOCUMENT)
                .setCellPhone(ConstantsDomain.CELLPHONE)
                .setBirthdate(ConstantsDomain.BIRTHDATE)
                .setEmail(ConstantsDomain.EMAIL)
                .setPassword(ConstantsDomain.PASSWORD)
                .build();
        when(rolPersistencePort.getRolByName(Constans.AUX_BODEGA)).thenReturn(rolSave);

        userUseCase.saveUser(userSave);

        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getRolByName(ConstantsDomain.AUX_BODEGA);
    }

    @Test
    void shouldThrowsExceptionWhenIsMenor() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomain.NAME)
                .setLastName(ConstantsDomain.LAST_NAME)
                .setDocumentNumber(ConstantsDomain.DOCUMENT)
                .setCellPhone(ConstantsDomain.CELLPHONE)
                .setBirthdate(LocalDate.now())
                .setEmail(ConstantsDomain.EMAIL)
                .setPassword(ConstantsDomain.PASSWORD)
                .build();

        assertThrows(MinorInvalidException.class, () -> {
            userUseCase.saveUser(userSave);
        });


        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getRolByName(ConstantsDomain.AUX_BODEGA);
    }

    @Test
    void shouldThrowsExceptionWhenDocumentNotNumber() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomain.NAME)
                .setLastName(ConstantsDomain.LAST_NAME)
                .setDocumentNumber(ConstantsDomain.LAST_NAME)
                .setCellPhone(ConstantsDomain.CELLPHONE)
                .setBirthdate(ConstantsDomain.BIRTHDATE)
                .setEmail(ConstantsDomain.EMAIL)
                .setPassword(ConstantsDomain.PASSWORD)
                .build();

        assertThrows(DocumentInvalidException.class, () -> {
            userUseCase.saveUser(userSave);
        });


        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getRolByName(ConstantsDomain.AUX_BODEGA);
    }

    @Test
    void shouldThrowsExceptionWhenEmailIsInvalid() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomain.NAME)
                .setLastName(ConstantsDomain.LAST_NAME)
                .setDocumentNumber(ConstantsDomain.DOCUMENT)
                .setCellPhone(ConstantsDomain.CELLPHONE)
                .setBirthdate(ConstantsDomain.BIRTHDATE)
                .setEmail(ConstantsDomain.PASSWORD)
                .setPassword(ConstantsDomain.PASSWORD)
                .build();

        assertThrows(UserEmailInvalidException.class, () -> {
            userUseCase.saveUser(userSave);
        });


        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getRolByName(ConstantsDomain.AUX_BODEGA);
    }

    @Test
    void shouldThrowsExceptionWhenEmailIsPresent() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomain.NAME)
                .setLastName(ConstantsDomain.LAST_NAME)
                .setDocumentNumber(ConstantsDomain.DOCUMENT)
                .setCellPhone(ConstantsDomain.CELLPHONE)
                .setBirthdate(ConstantsDomain.BIRTHDATE)
                .setEmail(ConstantsDomain.EMAIL)
                .setPassword(ConstantsDomain.PASSWORD)
                .build();
        when(userPersistencePort.getUserByEmail(ConstantsDomain.EMAIL)).thenReturn(ConstantsDomain.VALUE_TRUE);

        assertThrows(UserAlreadyExistsException.class, () -> {
            userUseCase.saveUser(userSave);
        });

        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getUserByEmail(ConstantsDomain.EMAIL);
        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getRolByName(ConstantsDomain.AUX_BODEGA);
    }

    @Test
    void shouldThrowsExceptionWhenPhoneIsInvalid() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomain.NAME)
                .setLastName(ConstantsDomain.LAST_NAME)
                .setDocumentNumber(ConstantsDomain.DOCUMENT)
                .setCellPhone(ConstantsDomain.EMAIL)
                .setBirthdate(ConstantsDomain.BIRTHDATE)
                .setEmail(ConstantsDomain.EMAIL)
                .setPassword(ConstantsDomain.PASSWORD)
                .build();

        assertThrows(PhoneNumberinvalidException.class, () -> {
            userUseCase.saveUser(userSave);
        });


        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomain.VALUE_0))
                .getRolByName(ConstantsDomain.AUX_BODEGA);
    }

    @Test
    void shouldThrowsExceptionWhenRolNotExist() {
        RolSave rolSave = null;
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomain.NAME)
                .setLastName(ConstantsDomain.LAST_NAME)
                .setDocumentNumber(ConstantsDomain.DOCUMENT)
                .setCellPhone(ConstantsDomain.CELLPHONE)
                .setBirthdate(ConstantsDomain.BIRTHDATE)
                .setEmail(ConstantsDomain.EMAIL)
                .setPassword(ConstantsDomain.PASSWORD)
                .build();
        when(rolPersistencePort.getRolByName(Constans.AUX_BODEGA)).thenReturn(rolSave);

        assertThrows(RolAuxBodegaInvalidException.class, () -> {
            userUseCase.saveUser(userSave);
        });

        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomain.VALUE_1))
                .getRolByName(ConstantsDomain.AUX_BODEGA);
    }
}
