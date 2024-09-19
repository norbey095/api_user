package com.emazon.api_user.infraestructure.output.adapter;

import com.emazon.api_user.domain.model.UserAuth;
import com.emazon.api_user.infraestructure.output.entity.RolEntity;
import com.emazon.api_user.infraestructure.output.entity.UserEntity;
import com.emazon.api_user.infraestructure.output.util.JwtService;
import com.emazon.api_user.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

class AuthenticationAdapterTest {
    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthenticationAdapter authenticationAdapter;

    private UserAuth userAuth;
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        RolEntity rol = new RolEntity();
        rol.setName(ConstantsInfTest.ROL_NAME);

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(ConstantsInfTest.PASSWORD);
        userEntity.setEmail(ConstantsInfTest.EMAIL);
        userEntity.setRol(rol);

        userAuth = new UserAuth();
        userAuth.setEmail(ConstantsInfTest.EMAIL);
        userAuth.setRole(ConstantsInfTest.ROL_NAME);

        authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(userEntity);
    }

    @Test
    void testAuthenticate_Success() {
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        UserAuth result = authenticationAdapter.authenticate(ConstantsInfTest.EMAIL, ConstantsInfTest.PASSWORD);

        assertNotNull(result);
        assertEquals(ConstantsInfTest.EMAIL, result.getEmail());
        assertEquals(ConstantsInfTest.ROL_NAME, result.getRole());
    }

    @Test
    void testAuthenticate_Failure() {
        Mockito.when(authenticationManager.authenticate(Mockito.any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException(ConstantsInfTest.ACCESS_DENE));

        UserAuth result = authenticationAdapter.authenticate(ConstantsInfTest.EMAIL, ConstantsInfTest.PASSWORD);

        assertNull(result);
    }

    @Test
    void testGetToken() {
        Mockito.when(jwtService.generateToken(Mockito.anyString(), Mockito.anyMap()))
                .thenReturn(ConstantsInfTest.EMAIL);

        String token = authenticationAdapter.getToken(userAuth);

        assertEquals(ConstantsInfTest.EMAIL, token);
    }
}