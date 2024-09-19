package com.emazon.api_user.apilication.handler.authentication;

import com.emazon.api_user.apilication.util.ConstantsApplicationTest;
import com.emazon.api_user.application.dto.authentication.AuthenticationRequestDto;
import com.emazon.api_user.application.handler.authetication.AuthenticationHandler;
import com.emazon.api_user.domain.api.IAuthenticationServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

class AuthenticationHandlerTest {

    @InjectMocks
    private AuthenticationHandler authenticationHandler;

    @Mock
    private IAuthenticationServicePort authenticationServicePort;

    private AuthenticationRequestDto authenticationRequestDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        authenticationRequestDto = new AuthenticationRequestDto(ConstantsApplicationTest.EMAIL,
                ConstantsApplicationTest.PASSWORD);
    }

    @Test
    void shouldAuthenticationSuccess() {

        when(authenticationServicePort.authentication(ConstantsApplicationTest.EMAIL,
                ConstantsApplicationTest.PASSWORD)).thenReturn(ConstantsApplicationTest.CELLPHONE);

        authenticationHandler.authentication(authenticationRequestDto);


        Mockito.verify(authenticationServicePort, Mockito.times(ConstantsApplicationTest.VERIFY_1_CALL))
                .authentication(ConstantsApplicationTest.EMAIL,
                        ConstantsApplicationTest.PASSWORD);
    }
}