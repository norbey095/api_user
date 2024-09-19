package com.emazon.api_user.apilication.handler;

import com.emazon.api_user.apilication.util.ConstantsApplicationTest;
import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.handler.UserHandler;
import com.emazon.api_user.application.mapper.UserMapper;
import com.emazon.api_user.domain.api.IUserServicePort;
import com.emazon.api_user.domain.model.UserSave;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserHandlerTest {

    @InjectMocks
    private UserHandler userHandler;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private UserMapper userMapper;

    private UserRequestDto userRequestDto;

    private UserSave userSave;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userRequestDto = UserRequestDto.builder()
                .name(ConstantsApplicationTest.NAME)
                .lastName(ConstantsApplicationTest.LAST_NAME)
                .documentNumber(ConstantsApplicationTest.DOCUMENT)
                .cellPhone(ConstantsApplicationTest.CELLPHONE)
                .birthdate(ConstantsApplicationTest.BIRTHDATE)
                .email(ConstantsApplicationTest.EMAIL)
                .password(ConstantsApplicationTest.PASSWORD)
                .build();

        userSave = UserSave.builder()
                .setName(ConstantsApplicationTest.NAME)
                .setLastName(ConstantsApplicationTest.LAST_NAME)
                .setDocumentNumber(ConstantsApplicationTest.DOCUMENT)
                .setCellPhone(ConstantsApplicationTest.CELLPHONE)
                .setBirthdate(ConstantsApplicationTest.BIRTHDATE)
                .setEmail(ConstantsApplicationTest.EMAIL)
                .setPassword(ConstantsApplicationTest.PASSWORD)
                .build();
    }

    @Test
    void shouldSaveUserAux() {
        when(userMapper.userRequestDtoToUserSave(userRequestDto)).thenReturn(userSave);

        userHandler.saveUser(userRequestDto, ConstantsApplicationTest.ROLE_AUX);


        verify(userMapper).userRequestDtoToUserSave(userRequestDto);
        verify(userServicePort).saveUser(userSave, ConstantsApplicationTest.ROLE_AUX);
    }

    @Test
    void shouldSaveUserClient() {
        when(userMapper.userRequestDtoToUserSave(userRequestDto)).thenReturn(userSave);

        userHandler.saveUser(userRequestDto, ConstantsApplicationTest.ROLE_CLIENT);


        verify(userMapper).userRequestDtoToUserSave(userRequestDto);
        verify(userServicePort).saveUser(userSave, ConstantsApplicationTest.ROLE_CLIENT);
    }

}
