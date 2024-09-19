package com.emazon.api_user.domain.usecase;

import com.emazon.api_user.domain.exception.*;
import com.emazon.api_user.domain.model.RolSave;
import com.emazon.api_user.domain.model.UserSave;
import com.emazon.api_user.domain.spi.IRolPersistencePort;
import com.emazon.api_user.domain.spi.IUserPersistencePort;
import com.emazon.api_user.domain.util.ConstantsDomainTest;
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
    void shouldCallSaveUserAuxInPersistencePort() {
        RolSave rolSave = new RolSave(ConstantsDomainTest.ROL_ID, ConstantsDomainTest.ROL_NAME
                , ConstantsDomainTest.ROL_DESCRIPTION);
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomainTest.NAME)
                .setLastName(ConstantsDomainTest.LAST_NAME)
                .setDocumentNumber(ConstantsDomainTest.DOCUMENT)
                .setCellPhone(ConstantsDomainTest.CELLPHONE)
                .setBirthdate(ConstantsDomainTest.BIRTHDATE)
                .setEmail(ConstantsDomainTest.EMAIL)
                .setPassword(ConstantsDomainTest.PASSWORD)
                .build();
        when(rolPersistencePort.getRolByName(ConstantsDomainTest.AUX_BODEGA)).thenReturn(rolSave);

        userUseCase.saveUser(userSave, ConstantsDomainTest.ROLE_AUX);

        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_1))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_1))
                .getRolByName(ConstantsDomainTest.ROLE_AUX);
    }

    @Test
    void shouldCallSaveUserClientInPersistencePort() {
        RolSave rolSave = new RolSave(ConstantsDomainTest.ROL_ID, ConstantsDomainTest.ROL_NAME
                , ConstantsDomainTest.ROL_DESCRIPTION);
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomainTest.NAME)
                .setLastName(ConstantsDomainTest.LAST_NAME)
                .setDocumentNumber(ConstantsDomainTest.DOCUMENT)
                .setCellPhone(ConstantsDomainTest.CELLPHONE)
                .setBirthdate(ConstantsDomainTest.BIRTHDATE)
                .setEmail(ConstantsDomainTest.EMAIL)
                .setPassword(ConstantsDomainTest.PASSWORD)
                .build();
        when(rolPersistencePort.getRolByName(ConstantsDomainTest.ROLE_CLIENT)).thenReturn(rolSave);

        userUseCase.saveUser(userSave, ConstantsDomainTest.ROLE_CLIENT);

        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_1))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_1))
                .getRolByName(ConstantsDomainTest.ROLE_CLIENT);
    }

    @Test
    void shouldThrowsExceptionWhenIsMenor() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomainTest.NAME)
                .setLastName(ConstantsDomainTest.LAST_NAME)
                .setDocumentNumber(ConstantsDomainTest.DOCUMENT)
                .setCellPhone(ConstantsDomainTest.CELLPHONE)
                .setBirthdate(LocalDate.now())
                .setEmail(ConstantsDomainTest.EMAIL)
                .setPassword(ConstantsDomainTest.PASSWORD)
                .build();

        assertThrows(MinorInvalidException.class, () -> {
            userUseCase.saveUser(userSave, ConstantsDomainTest.ROLE_AUX);
        });


        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_0))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_0))
                .getRolByName(ConstantsDomainTest.AUX_BODEGA);
    }

    @Test
    void shouldThrowsExceptionWhenDocumentNotNumber() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomainTest.NAME)
                .setLastName(ConstantsDomainTest.LAST_NAME)
                .setDocumentNumber(ConstantsDomainTest.LAST_NAME)
                .setCellPhone(ConstantsDomainTest.CELLPHONE)
                .setBirthdate(ConstantsDomainTest.BIRTHDATE)
                .setEmail(ConstantsDomainTest.EMAIL)
                .setPassword(ConstantsDomainTest.PASSWORD)
                .build();

        assertThrows(DocumentInvalidException.class, () -> {
            userUseCase.saveUser(userSave, ConstantsDomainTest.ROLE_AUX);
        });


        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_0))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_0))
                .getRolByName(ConstantsDomainTest.AUX_BODEGA);
    }

    @Test
    void shouldThrowsExceptionWhenEmailIsInvalid() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomainTest.NAME)
                .setLastName(ConstantsDomainTest.LAST_NAME)
                .setDocumentNumber(ConstantsDomainTest.DOCUMENT)
                .setCellPhone(ConstantsDomainTest.CELLPHONE)
                .setBirthdate(ConstantsDomainTest.BIRTHDATE)
                .setEmail(ConstantsDomainTest.PASSWORD)
                .setPassword(ConstantsDomainTest.PASSWORD)
                .build();

        assertThrows(UserEmailInvalidException.class, () -> {
            userUseCase.saveUser(userSave, ConstantsDomainTest.ROLE_AUX);
        });


        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_0))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_0))
                .getRolByName(ConstantsDomainTest.AUX_BODEGA);
    }

    @Test
    void shouldThrowsExceptionWhenEmailIsPresent() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomainTest.NAME)
                .setLastName(ConstantsDomainTest.LAST_NAME)
                .setDocumentNumber(ConstantsDomainTest.DOCUMENT)
                .setCellPhone(ConstantsDomainTest.CELLPHONE)
                .setBirthdate(ConstantsDomainTest.BIRTHDATE)
                .setEmail(ConstantsDomainTest.EMAIL)
                .setPassword(ConstantsDomainTest.PASSWORD)
                .build();
        when(userPersistencePort.getUserByEmail(ConstantsDomainTest.EMAIL)).thenReturn(ConstantsDomainTest.VALUE_TRUE);

        assertThrows(UserAlreadyExistsException.class, () -> {
            userUseCase.saveUser(userSave, ConstantsDomainTest.ROLE_AUX);
        });

        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_1))
                .getUserByEmail(ConstantsDomainTest.EMAIL);
        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_0))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_0))
                .getRolByName(ConstantsDomainTest.AUX_BODEGA);
    }

    @Test
    void shouldThrowsExceptionWhenPhoneIsInvalid() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomainTest.NAME)
                .setLastName(ConstantsDomainTest.LAST_NAME)
                .setDocumentNumber(ConstantsDomainTest.DOCUMENT)
                .setCellPhone(ConstantsDomainTest.EMAIL)
                .setBirthdate(ConstantsDomainTest.BIRTHDATE)
                .setEmail(ConstantsDomainTest.EMAIL)
                .setPassword(ConstantsDomainTest.PASSWORD)
                .build();

        assertThrows(PhoneNumberinvalidException.class, () -> {
            userUseCase.saveUser(userSave, ConstantsDomainTest.ROLE_AUX);
        });


        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_0))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_0))
                .getRolByName(ConstantsDomainTest.AUX_BODEGA);
    }

    @Test
    void shouldThrowsExceptionWhenRolNotExist() {
        RolSave rolSave = null;
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomainTest.NAME)
                .setLastName(ConstantsDomainTest.LAST_NAME)
                .setDocumentNumber(ConstantsDomainTest.DOCUMENT)
                .setCellPhone(ConstantsDomainTest.CELLPHONE)
                .setBirthdate(ConstantsDomainTest.BIRTHDATE)
                .setEmail(ConstantsDomainTest.EMAIL)
                .setPassword(ConstantsDomainTest.PASSWORD)
                .build();
        when(rolPersistencePort.getRolByName(ConstantsDomainTest.AUX_BODEGA)).thenReturn(rolSave);

        assertThrows(RolAuxBodegaInvalidException.class, () -> {
            userUseCase.saveUser(userSave, ConstantsDomainTest.ROLE_AUX);
        });

        Mockito.verify(userPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_1))
                .encryptedPassword(userSave.getPassword());
        Mockito.verify(rolPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_1))
                .getRolByName(ConstantsDomainTest.AUX_BODEGA);
    }
}
