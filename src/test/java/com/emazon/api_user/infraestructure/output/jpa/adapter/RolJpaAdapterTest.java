package com.emazon.api_user.infraestructure.output.jpa.adapter;

import com.emazon.api_user.domain.model.RolSave;
import com.emazon.api_user.infraestructure.output.jpa.entity.RolEntity;
import com.emazon.api_user.infraestructure.output.jpa.mapper.RolEntityMapper;
import com.emazon.api_user.infraestructure.output.jpa.repository.IRolRepository;
import com.emazon.api_user.infraestructure.util.Constans;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RolJpaAdapterTest {
    @Mock
    private IRolRepository rolRepository;

    @Mock
    private RolEntityMapper rolEntityMapper;

    @Spy
    @InjectMocks
    private RolJpaAdapter rolJpaAdapter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetRolByNameSuccess() {
        RolEntity rolEntity = new RolEntity(Constans.ROL_ID, Constans.ROL_NAME
                , Constans.ROL_DESCRIPTION);
        RolSave rolSave = new RolSave(Constans.ROL_ID, Constans.ROL_NAME
                , Constans.ROL_DESCRIPTION);
        Mockito.when(rolRepository.getRolByName(Constans.NAME))
                .thenReturn(rolEntity);
        Mockito.when(rolEntityMapper.rolEntityToRolSave(rolEntity))
                .thenReturn(rolSave);

        RolSave result = rolJpaAdapter.getRolByName(Constans.NAME);

        assertEquals(result.getId(),rolEntity.getId());
        assertEquals(result.getName(),rolEntity.getName());
        assertEquals(result.getDescription(),rolEntity.getDescription());
    }
}
