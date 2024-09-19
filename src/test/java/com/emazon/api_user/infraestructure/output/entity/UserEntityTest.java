package com.emazon.api_user.infraestructure.output.entity;

import com.emazon.api_user.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;


class UserEntityTest {

    private UserEntity userEntity;

    @BeforeEach
    public void setUp() {
        RolEntity rolEntity = new RolEntity();
        rolEntity.setName(ConstantsInfTest.ROL_NAME);

        userEntity = new UserEntity();
        userEntity.setPassword(ConstantsInfTest.PASSWORD);
        userEntity.setEmail(ConstantsInfTest.EMAIL);
        userEntity.setRol(rolEntity);

    }

    @Test
    void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = userEntity.getAuthorities();

        assertNotNull(authorities);
        assertEquals(ConstantsInfTest.VALUE_1, authorities.size());
        assertEquals(ConstantsInfTest.ROL_NAME_AUXILIAR, authorities.iterator().next().getAuthority());
    }

    @Test
    void testGetPassword() {
        assertEquals(ConstantsInfTest.PASSWORD, userEntity.getPassword());
    }

    @Test
    void testGetUsername() {
        assertEquals(ConstantsInfTest.EMAIL, userEntity.getUsername());
    }
}