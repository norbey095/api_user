package com.emazon.api_user.apilication.mapper;

import com.emazon.api_user.apilication.util.ConstantsApplicationTest;
import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.mapper.UserMapper;
import com.emazon.api_user.domain.model.UserSave;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void testUserRequestDtoToUserSave() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name(ConstantsApplicationTest.NAME)
                .lastName(ConstantsApplicationTest.LAST_NAME)
                .documentNumber(ConstantsApplicationTest.DOCUMENT)
                .cellPhone(ConstantsApplicationTest.CELLPHONE)
                .birthdate(ConstantsApplicationTest.BIRTHDATE)
                .email(ConstantsApplicationTest.EMAIL)
                .password(ConstantsApplicationTest.PASSWORD)
                .build();

        UserSave usertestSave = userMapper.userRequestDtoToUserSave(userRequestDto);

        assertNotNull(usertestSave);
        assertEquals(userRequestDto.getName(),usertestSave.getName());
        assertEquals(userRequestDto.getLastName(),usertestSave.getLastName());
        assertEquals(userRequestDto.getDocumentNumber(), usertestSave.getDocumentNumber());
        assertEquals(userRequestDto.getCellPhone(), usertestSave.getCellPhone());
        assertEquals(userRequestDto.getBirthdate(), usertestSave.getBirthdate());
        assertEquals(userRequestDto.getEmail(), usertestSave.getEmail());
        assertEquals(userRequestDto.getPassword(), usertestSave.getPassword());

    }

    @Test
    void testUserRequestDtoToUserSaveMap() {
        UserRequestDto userRequestDto = UserRequestDto.builder()
                .name(ConstantsApplicationTest.NAME)
                .lastName(ConstantsApplicationTest.LAST_NAME)
                .documentNumber(ConstantsApplicationTest.DOCUMENT)
                .cellPhone(ConstantsApplicationTest.CELLPHONE)
                .birthdate(ConstantsApplicationTest.BIRTHDATE)
                .email(ConstantsApplicationTest.EMAIL)
                .password(ConstantsApplicationTest.PASSWORD)
                .build();

        UserSave usertestSave = userMapper.map(userRequestDto);

        assertNotNull(usertestSave);
        assertEquals(userRequestDto.getName(),usertestSave.getName());
        assertEquals(userRequestDto.getLastName(),usertestSave.getLastName());
        assertEquals(userRequestDto.getDocumentNumber(), usertestSave.getDocumentNumber());
        assertEquals(userRequestDto.getCellPhone(), usertestSave.getCellPhone());
        assertEquals(userRequestDto.getBirthdate(), usertestSave.getBirthdate());
        assertEquals(userRequestDto.getEmail(), usertestSave.getEmail());
        assertEquals(userRequestDto.getPassword(), usertestSave.getPassword());

    }


}
