package com.emazon.api_user.infraestructure.mapper;

import com.emazon.api_user.domain.model.RolSave;
import com.emazon.api_user.infraestructure.output.entity.RolEntity;
import com.emazon.api_user.infraestructure.output.mapper.RolEntityMapper;
import com.emazon.api_user.infraestructure.util.ConstantsInfTest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class RolEntityMapperTest {

    private final RolEntityMapper rolEntityMapper = Mappers.getMapper( RolEntityMapper.class);

    @Test
    void testRolEntityToRolSave() {
        RolEntity rolEntity = new RolEntity();
        rolEntity.setId(ConstantsInfTest.ROL_ID);
        rolEntity.setName( ConstantsInfTest.ROL_NAME);
        rolEntity.setDescription(ConstantsInfTest.ROL_DESCRIPTION);

        RolSave rolSave = rolEntityMapper.rolEntityToRolSave(rolEntity);

        assertNotNull(rolSave);
        assertEquals(rolSave.getId(),rolEntity.getId());
        assertEquals(rolSave.getName(),rolEntity.getName());
        assertEquals(rolSave.getDescription(), rolEntity.getDescription());
    }

    @Test
    void testRolEntityToRolSaveNull() {
        RolSave rolSave = rolEntityMapper.rolEntityToRolSave(null);

        assertNull(rolSave);
    }
}
