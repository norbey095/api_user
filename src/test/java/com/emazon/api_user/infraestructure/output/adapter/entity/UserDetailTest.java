package com.emazon.api_user.infraestructure.output.adapter.entity;

import com.emazon.api_user.infraestructure.output.entity.RolEntity;
import com.emazon.api_user.infraestructure.output.entity.UserDetail;
import com.emazon.api_user.infraestructure.output.entity.UserEntity;
import com.emazon.api_user.infraestructure.util.Constans;
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
        rolEntity.setName(Constans.ROL_NAME);

        userEntity = Mockito.mock(UserEntity.class);
        userEntity.setPassword(Constans.PASSWORD);
        userEntity.setEmail(Constans.EMAIL);
        userEntity.setRol(rolEntity);

        userDetail = new UserDetail(userEntity);
    }

    @Test
    void testUserNameCorrect() {
        Mockito.when(userEntity.getEmail()).thenReturn(Constans.EMAIL);
        String userName = userDetail.getUsername();

        assertEquals(userName, userEntity.getEmail());
    }

    @Test
    void testGetPassword() {
        Mockito.when(userEntity.getPassword()).thenReturn(Constans.PASSWORD);
        String password = userDetail.getPassword();

        assertEquals(Constans.PASSWORD, password);
    }

    @Test
    void testGetAuthorities() {
        Mockito.when(userEntity.getRol()).thenReturn(rolEntity);
        Mockito.when(rolEntity.getName()).thenReturn(Constans.ROL_NAME);
        Collection<? extends GrantedAuthority> authorities = userDetail.getAuthorities();

        assertEquals(Constans.ROL_ID, authorities.size());
        assertTrue(authorities.contains(new SimpleGrantedAuthority(Constans.ROL_NAME_AUXILIAR)));
    }
}