package com.emazon.api_user.infraestructure.mapper;

import com.emazon.api_user.domain.model.RolSave;
import com.emazon.api_user.infraestructure.output.jpa.entity.RolEntity;
import com.emazon.api_user.infraestructure.output.jpa.mapper.RolEntityMapper;
import com.emazon.api_user.infraestructure.util.Constans;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class RolEntityMapperTest {

    private final RolEntityMapper rolEntityMapper = Mappers.getMapper( RolEntityMapper.class);

    @Test
    void testRolEntityToRolSave() {
        RolEntity rolEntity = new RolEntity(Constans.ROL_ID, Constans.ROL_NAME
                , Constans.ROL_DESCRIPTION);

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
