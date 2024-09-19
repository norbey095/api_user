package com.emazon.api_user.infraestructure.output.adapter.securityconfig;

import com.emazon.api_user.application.dto.authentication.AuthenticationRequestDto;
import com.emazon.api_user.application.dto.authentication.AuthenticationResponseDto;
import com.emazon.api_user.infraestructure.output.adapter.securityconfig.jwtconfiguration.JwtService;
import com.emazon.api_user.infraestructure.output.entity.UserEntity;
import com.emazon.api_user.infraestructure.output.repository.IUserRepository;
import com.emazon.api_user.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private IUserRepository repository;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private Authentication authentication;

    @Mock
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticate_Success() {
        String email = ConstantsInfTest.EMAIL_EJEM;
        String password = ConstantsInfTest.PASSWORD_NAME;

        AuthenticationRequestDto request = new AuthenticationRequestDto(email, password);
        String token = ConstantsInfTest.JWTTOKEN;

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(password);

        doReturn(authentication).when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(userDetails.getUsername()).thenReturn(email);
        when(repository.findByEmail(email)).thenReturn(Optional.of(userEntity));
        when(jwtService.generateToken(email, userDetails)).thenReturn(token);

        AuthenticationResponseDto response = authenticationService.authenticate(request);

        assertEquals(token, response.getToken());
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, times(ConstantsInfTest.VALUE_1))
                .generateToken(email,userDetails);
    }

    @Test
    void testAuthenticate_UserNotFound() {
        String email = ConstantsInfTest.EMAIL_EJEM;
        String password = ConstantsInfTest.PASSWORD_NAME;
        AuthenticationRequestDto request = new AuthenticationRequestDto(email, password);

        doThrow(new IllegalArgumentException()).when(authenticationManager)
                .authenticate(any(UsernamePasswordAuthenticationToken.class));
        when(authentication.getPrincipal()).thenReturn(userDetails);
        when(repository.findByEmail(email)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            authenticationService.authenticate(request);
        });
    }
}