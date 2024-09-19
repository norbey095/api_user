package com.emazon.api_user.domain.model;

import com.emazon.api_user.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserSaveTest {

    @Test
    void shouldCreateArticleWhenNameAndDescriptionAreValid() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomain.NAME)
                .setLastName(ConstantsDomain.LAST_NAME)
                .setDocumentNumber(ConstantsDomain.DOCUMENT)
                .setCellPhone(ConstantsDomain.CELLPHONE)
                .setBirthdate(ConstantsDomain.BIRTHDATE)
                .setEmail(ConstantsDomain.EMAIL)
                .setPassword(ConstantsDomain.PASSWORD)
                .build();

        assertNotNull(userSave);
        assertEquals(ConstantsDomain.NAME,userSave.getName());
        assertEquals(ConstantsDomain.LAST_NAME,userSave.getLastName());
        assertEquals(ConstantsDomain.DOCUMENT, userSave.getDocumentNumber());
        assertEquals(ConstantsDomain.CELLPHONE, userSave.getCellPhone());
        assertEquals(ConstantsDomain.BIRTHDATE, userSave.getBirthdate());
        assertEquals(ConstantsDomain.EMAIL, userSave.getEmail());
        assertEquals(ConstantsDomain.PASSWORD, userSave.getPassword());
    }
}
