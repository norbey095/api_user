package com.emazon.api_user.domain.model;

import com.emazon.api_user.domain.util.ConstantsDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


 class RolSaveTest {

    @Test
    void shouldCreateUserConstructor() {
        RolSave rolSave = new RolSave(ConstantsDomain.ROL_ID, ConstantsDomain.ROL_NAME
                , ConstantsDomain.ROL_DESCRIPTION);

        assertNotNull(rolSave);
        assertEquals(ConstantsDomain.ROL_ID, rolSave.getId());
        assertEquals(ConstantsDomain.ROL_NAME, rolSave.getName());
        assertEquals(ConstantsDomain.ROL_DESCRIPTION, rolSave.getDescription());
    }

     @Test
     void shouldCreateUserSet() {
         RolSave rolSave = new RolSave();
         rolSave.setId(ConstantsDomain.ROL_ID);
         rolSave.setName(ConstantsDomain.ROL_NAME);
         rolSave.setDescription(ConstantsDomain.ROL_DESCRIPTION);

         assertNotNull(rolSave);
         assertEquals(ConstantsDomain.ROL_ID, rolSave.getId());
         assertEquals(ConstantsDomain.ROL_NAME, rolSave.getName());
         assertEquals(ConstantsDomain.ROL_DESCRIPTION, rolSave.getDescription());
     }
}
