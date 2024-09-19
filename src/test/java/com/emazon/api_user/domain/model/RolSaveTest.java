package com.emazon.api_user.domain.model;

import com.emazon.api_user.domain.util.ConstantsDomainTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


 class RolSaveTest {

    @Test
    void shouldCreateUserConstructor() {
        RolSave rolSave = new RolSave(ConstantsDomainTest.ROL_ID, ConstantsDomainTest.ROL_NAME
                , ConstantsDomainTest.ROL_DESCRIPTION);

        assertNotNull(rolSave);
        assertEquals(ConstantsDomainTest.ROL_ID, rolSave.getId());
        assertEquals(ConstantsDomainTest.ROL_NAME, rolSave.getName());
        assertEquals(ConstantsDomainTest.ROL_DESCRIPTION, rolSave.getDescription());
    }

     @Test
     void shouldCreateUserSet() {
         RolSave rolSave = new RolSave();
         rolSave.setId(ConstantsDomainTest.ROL_ID);
         rolSave.setName(ConstantsDomainTest.ROL_NAME);
         rolSave.setDescription(ConstantsDomainTest.ROL_DESCRIPTION);

         assertNotNull(rolSave);
         assertEquals(ConstantsDomainTest.ROL_ID, rolSave.getId());
         assertEquals(ConstantsDomainTest.ROL_NAME, rolSave.getName());
         assertEquals(ConstantsDomainTest.ROL_DESCRIPTION, rolSave.getDescription());
     }
}
