package com.emazon.api_user.domain.usecase;

import com.emazon.api_user.domain.exception.CredentialsException;
import com.emazon.api_user.domain.model.UserAuth;
import com.emazon.api_user.domain.spi.IAthenticationPersistencePort;
import com.emazon.api_user.domain.util.ConstantsDomainTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class AuthenticationUseCaseTest {
    @Mock
    private IAthenticationPersistencePort athenticationPersistencePort;

    @InjectMocks
    private AuthenticationUseCase authenticationUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldCallAuthenticationInPersistencePort() {
        when(athenticationPersistencePort.authenticate(ConstantsDomainTest.EMAIL,ConstantsDomainTest.PASSWORD))
                .thenReturn(new UserAuth());
        when(athenticationPersistencePort.getToken(Mockito.any(UserAuth.class)))
                .thenReturn(ConstantsDomainTest.CELLPHONE);

        authenticationUseCase.authentication(ConstantsDomainTest.EMAIL,ConstantsDomainTest.PASSWORD);

        Mockito.verify(athenticationPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_1))
                .authenticate(ConstantsDomainTest.EMAIL,ConstantsDomainTest.PASSWORD);
        Mockito.verify(athenticationPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_1))
                .getToken(Mockito.any(UserAuth.class));
    }

    @Test
    void shouldReturnCredentialsException() {
        when(athenticationPersistencePort.authenticate(ConstantsDomainTest.EMAIL,ConstantsDomainTest.PASSWORD))
                .thenReturn(null);

        assertThrows(CredentialsException.class, () -> {
            authenticationUseCase.authentication(ConstantsDomainTest.EMAIL,ConstantsDomainTest.PASSWORD);
        });


        Mockito.verify(athenticationPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_1))
                .authenticate(ConstantsDomainTest.EMAIL,ConstantsDomainTest.PASSWORD);
        Mockito.verify(athenticationPersistencePort, Mockito.times(ConstantsDomainTest.VALUE_0))
                .getToken(Mockito.any(UserAuth.class));
    }
}