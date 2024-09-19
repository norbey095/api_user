package com.emazon.api_user.infraestructure.output.adapter.adapter.securityconfig;

import com.emazon.api_user.infraestructure.output.adapter.securityconfig.UserDetailService;
import com.emazon.api_user.infraestructure.output.entity.UserEntity;
import com.emazon.api_user.infraestructure.output.repository.IUserRepository;
import com.emazon.api_user.infraestructure.util.Constans;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDetailsServiceTest {

    private IUserRepository userRepository;
    private UserDetailService userDetailService;

    @BeforeEach
    void setUp() {
        userRepository = mock(IUserRepository.class);
        userDetailService = new UserDetailService(userRepository);
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        String email = Constans.EMAIL;
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(email);
        Mockito.when(userRepository.findByEmail(email))
                .thenReturn(Optional.of(userEntity));

        UserDetails userDetails = userDetailService.loadUserByUsername(email);

        assertNotNull(userDetails);
        assertEquals(email, userDetails.getUsername());
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        String email = Constans.EMAIL;
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        UsernameNotFoundException thrownException = assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailService.loadUserByUsername(email),
                Constans.USER_NOT_FOUND_POINTS+email
        );

        assertEquals(Constans.USER_NOT_FOUND_POINTS+email, thrownException.getMessage());
    }
}