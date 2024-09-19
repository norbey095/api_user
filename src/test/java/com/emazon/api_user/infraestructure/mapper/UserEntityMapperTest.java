package com.emazon.api_user.infraestructure.mapper;

import com.emazon.api_user.domain.model.RolSave;
import com.emazon.api_user.domain.model.UserSave;
import com.emazon.api_user.infraestructure.output.entity.UserEntity;
import com.emazon.api_user.infraestructure.output.mapper.UserEntityMapper;
import com.emazon.api_user.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityMapperTest {

    private final UserEntityMapper userEntityMapper = Mappers.getMapper( UserEntityMapper.class);

    @Test
    void testUserToUserEntity() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsInfTest.NAME)
                .setLastName(ConstantsInfTest.LAST_NAME)
                .setDocumentNumber(ConstantsInfTest.DOCUMENT)
                .setCellPhone(ConstantsInfTest.EMAIL)
                .setBirthdate(ConstantsInfTest.BIRTHDATE)
                .setEmail(ConstantsInfTest.EMAIL)
                .setPassword(ConstantsInfTest.PASSWORD)
                .setRol(new RolSave(ConstantsInfTest.ROL_ID, ConstantsInfTest.NAME, ConstantsInfTest.ROL_DESCRIPTION))
                .build();

        UserEntity userEntity = userEntityMapper.userToUserEntity(userSave);

        assertNotNull(userEntity);
        assertEquals(userSave.getName(),userEntity.getName());
        assertEquals(userSave.getLastName(),userEntity.getLastName());
        assertEquals(Integer.valueOf(userSave.getDocumentNumber()), userEntity.getDocumentNumber());
        assertEquals(userSave.getCellPhone(), userEntity.getCellPhone());
        assertEquals(userSave.getBirthdate(), userEntity.getBirthdate());
        assertEquals(userSave.getEmail(), userEntity.getEmail());
        assertEquals(userSave.getPassword(), userEntity.getPassword());
    }

    @Test
    void testUserToUserEntityNull() {
        UserEntity userEntity = userEntityMapper.userToUserEntity(null);

        assertNull(userEntity);
    }
}
