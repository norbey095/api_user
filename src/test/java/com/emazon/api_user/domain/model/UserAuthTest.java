package com.emazon.api_user.domain.model;

import com.emazon.api_user.domain.util.ConstantsDomainTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class UserAuthTest {

    @Test
    void shouldCreateUserAuthConstructor() {
        UserAuth userAuth = new UserAuth(ConstantsDomainTest.EMAIL, ConstantsDomainTest.ROLE_AUX);

        assertNotNull(userAuth);
        assertEquals(ConstantsDomainTest.EMAIL, userAuth.getEmail());
        assertEquals(ConstantsDomainTest.ROLE_AUX, userAuth.getRole());
    }

    @Test
    void shouldCreateUserAuthSet() {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail(ConstantsDomainTest.EMAIL);
        userAuth.setRole(ConstantsDomainTest.ROLE_AUX);

        assertNotNull(userAuth);
        assertEquals(ConstantsDomainTest.EMAIL, userAuth.getEmail());
        assertEquals(ConstantsDomainTest.ROLE_AUX, userAuth.getRole());
    }
}