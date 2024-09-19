package com.emazon.api_user.apilication.handler;

import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.handler.UserHandler;
import com.emazon.api_user.application.mapper.UserMapper;
import com.emazon.api_user.domain.api.IUserServicePort;
import com.emazon.api_user.domain.model.UserSave;
import com.emazon.api_user.domain.util.ConstantsDomain;
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
                .name(ConstantsDomain.NAME)
                .lastName(ConstantsDomain.LAST_NAME)
                .documentNumber(ConstantsDomain.DOCUMENT)
                .cellPhone(ConstantsDomain.CELLPHONE)
                .birthdate(ConstantsDomain.BIRTHDATE)
                .email(ConstantsDomain.EMAIL)
                .password(ConstantsDomain.PASSWORD)
                .build();

        userSave = UserSave.builder()
                .setName(ConstantsDomain.NAME)
                .setLastName(ConstantsDomain.LAST_NAME)
                .setDocumentNumber(ConstantsDomain.DOCUMENT)
                .setCellPhone(ConstantsDomain.CELLPHONE)
                .setBirthdate(ConstantsDomain.BIRTHDATE)
                .setEmail(ConstantsDomain.EMAIL)
                .setPassword(ConstantsDomain.PASSWORD)
                .build();
    }

    @Test
    void shouldSaveUser() {
        when(userMapper.userRequestDtoToUserSave(userRequestDto)).thenReturn(userSave);

        userHandler.saveUser(userRequestDto);


        verify(userMapper).userRequestDtoToUserSave(userRequestDto);
        verify(userServicePort).saveUser(userSave);
    }

}
