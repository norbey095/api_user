package com.emazon.api_user.infraestructure.output.adapter.entity;

import com.emazon.api_user.infraestructure.output.entity.RolEntity;
import com.emazon.api_user.infraestructure.output.entity.UserDetail;
import com.emazon.api_user.infraestructure.output.entity.UserEntity;
import com.emazon.api_user.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UserDetailTest {

    private UserDetail userDetail;
    private UserEntity userEntity;
    private RolEntity rolEntity ;

    @BeforeEach
    public void setUp() {
        rolEntity = Mockito.mock(RolEntity.class);
        rolEntity.setName(ConstantsInfTest.ROL_NAME);

        userEntity = Mockito.mock(UserEntity.class);
        userEntity.setPassword(ConstantsInfTest.PASSWORD);
        userEntity.setEmail(ConstantsInfTest.EMAIL);
        userEntity.setRol(rolEntity);

        userDetail = new UserDetail(userEntity);
    }

    @Test
    void testUserNameCorrect() {
        Mockito.when(userEntity.getEmail()).thenReturn(ConstantsInfTest.EMAIL);
        String userName = userDetail.getUsername();

        assertEquals(userName, userEntity.getEmail());
    }

    @Test
    void testGetPassword() {
        Mockito.when(userEntity.getPassword()).thenReturn(ConstantsInfTest.PASSWORD);
        String password = userDetail.getPassword();

        assertEquals(ConstantsInfTest.PASSWORD, password);
    }

    @Test
    void testGetAuthorities() {
        Mockito.when(userEntity.getRol()).thenReturn(rolEntity);
        Mockito.when(rolEntity.getName()).thenReturn(ConstantsInfTest.ROL_NAME);
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();

        assertEquals(ConstantsInfTest.ROL_ID, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(ConstantsInfTest.ROL_NAME_AUXILIAR)));
    }
}