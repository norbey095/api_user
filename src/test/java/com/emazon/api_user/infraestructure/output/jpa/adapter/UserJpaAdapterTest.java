package com.emazon.api_user.infraestructure.output.jpa.adapter;

import com.emazon.api_user.domain.model.UserSave;
import com.emazon.api_user.infraestructure.output.jpa.entity.UserEntity;
import com.emazon.api_user.infraestructure.output.jpa.mapper.UserEntityMapper;
import com.emazon.api_user.infraestructure.output.jpa.repository.IUserRepository;
import com.emazon.api_user.infraestructure.output.jpa.util.PasswordUtil;
import com.emazon.api_user.infraestructure.util.Constans;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserJpaAdapterTest {
    @Mock
    private IUserRepository userRepository;

    @Mock
    private UserEntityMapper userEntityMapper;

    @Spy
    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserSavesSuccessfully() {
        UserSave userSave = UserSave.builder()
                .setName(Constans.NAME)
                .setLastName(Constans.LAST_NAME)
                .setDocumentNumber(Constans.DOCUMENT)
                .setCellPhone(Constans.EMAIL)
                .setBirthdate(Constans.BIRTHDATE)
                .setEmail(Constans.EMAIL)
                .setPassword(Constans.PASSWORD)
                .build();
        UserEntity userEntity = new UserEntity();
        Mockito.when(userEntityMapper.userToUserEntity(userSave)).thenReturn(userEntity);

        userJpaAdapter.saveUser(userSave);

        Mockito.verify(userRepository, Mockito.times(Constans.ROL_ID)).save(userEntity);
    }

    @Test
    void testGetUserByEmailSuccess() {
        UserEntity userEntity = new UserEntity();
        Mockito.when(userRepository.findByEmail(Constans.EMAIL))
                .thenReturn(Optional.of(userEntity));

        boolean result = userJpaAdapter.getUserByEmail(Constans.EMAIL);

        assertTrue(result);
    }

    @Test
    void testEncryptedPassword() {
        String plainPassword = Constans.PASSWORD_ENCRYPT;

        String result = userJpaAdapter.encryptedPassword(plainPassword);

        assertNotNull(result);
        assertNotEquals(plainPassword, result);
        assertTrue(PasswordUtil.checkPassword(plainPassword, result));
    }
}
