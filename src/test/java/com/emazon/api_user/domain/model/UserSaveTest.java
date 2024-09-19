package com.emazon.api_user.domain.model;

import com.emazon.api_user.domain.util.ConstantsDomainTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserSaveTest {

    @Test
    void shouldCreateArticleWhenNameAndDescriptionAreValid() {
        UserSave userSave = UserSave.builder()
                .setName(ConstantsDomainTest.NAME)
                .setLastName(ConstantsDomainTest.LAST_NAME)
                .setDocumentNumber(ConstantsDomainTest.DOCUMENT)
                .setCellPhone(ConstantsDomainTest.CELLPHONE)
                .setBirthdate(ConstantsDomainTest.BIRTHDATE)
                .setEmail(ConstantsDomainTest.EMAIL)
                .setPassword(ConstantsDomainTest.PASSWORD)
                .build();

        assertNotNull(userSave);
        assertEquals(ConstantsDomainTest.NAME,userSave.getName());
        assertEquals(ConstantsDomainTest.LAST_NAME,userSave.getLastName());
        assertEquals(ConstantsDomainTest.DOCUMENT, userSave.getDocumentNumber());
        assertEquals(ConstantsDomainTest.CELLPHONE, userSave.getCellPhone());
        assertEquals(ConstantsDomainTest.BIRTHDATE, userSave.getBirthdate());
        assertEquals(ConstantsDomainTest.EMAIL, userSave.getEmail());
        assertEquals(ConstantsDomainTest.PASSWORD, userSave.getPassword());
    }
}
