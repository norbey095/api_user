package com.emazon.api_user.infraestructure.output.mapper;

import com.emazon.api_user.domain.model.RolSave;
import com.emazon.api_user.infraestructure.output.entity.RolEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RolEntityMapper {

    RolSave rolEntityToRolSave(RolEntity rolEntity);

}