package com.emazon.api_user.infraestructure.output.jpa.adapter;


import com.emazon.api_user.domain.model.RolSave;
import com.emazon.api_user.domain.spi.IRolPersistencePort;
import com.emazon.api_user.infraestructure.output.jpa.mapper.RolEntityMapper;
import com.emazon.api_user.infraestructure.output.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RolJpaAdapter implements IRolPersistencePort {

    private final IRolRepository rolRepository;
    private final RolEntityMapper rolEntityMapper;

    @Override
    public RolSave getRolByName(String name) {
        return rolEntityMapper.rolEntityToRolSave(rolRepository.getRolByName(name));
    }
}
