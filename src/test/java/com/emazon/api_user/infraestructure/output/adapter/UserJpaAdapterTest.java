package com.emazon.api_user.infraestructure.output.adapter;

import com.emazon.api_user.domain.model.UserSave;
import com.emazon.api_user.infraestructure.output.entity.UserEntity;
import com.emazon.api_user.infraestructure.output.mapper.UserEntityMapper;
import com.emazon.api_user.infraestructure.output.repository.IUserRepository;
import com.emazon.api_user.infraestructure.util.PasswordUtil;
import com.emazon.api_user.infraestructure.util.ConstantsInfTest;
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
                .setName(ConstantsInfTest.NAME)
                .setLastName(ConstantsInfTest.LAST_NAME)
                .setDocumentNumber(ConstantsInfTest.DOCUMENT)
                .setCellPhone(ConstantsInfTest.EMAIL)
                .setBirthdate(ConstantsInfTest.BIRTHDATE)
                .setEmail(ConstantsInfTest.EMAIL)
                .setPassword(ConstantsInfTest.PASSWORD)
                .build();
        UserEntity userEntity = new UserEntity();
        Mockito.when(userEntityMapper.userToUserEntity(userSave)).thenReturn(userEntity);

        userJpaAdapter.saveUser(userSave);

        Mockito.verify(userRepository, Mockito.times(ConstantsInfTest.ROL_ID)).save(userEntity);
    }

    @Test
    void testGetUserByEmailSuccess() {
        UserEntity userEntity = new UserEntity();
        Mockito.when(userRepository.findByEmail(ConstantsInfTest.EMAIL))
                .thenReturn(Optional.of(userEntity));

        boolean result = userJpaAdapter.getUserByEmail(ConstantsInfTest.EMAIL);

        assertTrue(result);
    }

    @Test
    void testEncryptedPassword() {
        String plainPassword = ConstantsInfTest.PASSWORD_ENCRYPT;

        String result = userJpaAdapter.encryptedPassword(plainPassword);

        assertNotNull(result);
        assertNotEquals(plainPassword, result);
        assertTrue(PasswordUtil.checkPassword(plainPassword, result));
    }
}
