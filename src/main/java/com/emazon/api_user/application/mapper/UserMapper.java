package com.emazon.api_user.application.mapper;

import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.domain.model.UserSave;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserSave userRequestDtoToUserSave(UserRequestDto userRequestDto);

    default UserSave map(UserRequestDto dto) {
        return UserSave.builder()
                .setName(dto.getName())
                .setLastName(dto.getLastName())
                .setDocumentNumber(dto.getDocumentNumber())
                .setCellPhone(dto.getCellPhone())
                .setBirthdate(dto.getBirthdate())
                .setEmail(dto.getEmail())
                .setPassword(dto.getPassword())
                .build();
    }

}
