package com.emazon.api_user.infraestructure.output.mapper;

import com.emazon.api_user.domain.model.UserSave;
import com.emazon.api_user.infraestructure.output.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserEntityMapper {

    UserEntity userToUserEntity(UserSave userSave);

}
