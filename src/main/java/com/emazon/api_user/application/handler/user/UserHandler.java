package com.emazon.api_user.application.handler.user;

import com.emazon.api_user.application.dto.ResponseSuccess;
import com.emazon.api_user.application.dto.UserRequestDto;
import com.emazon.api_user.application.mapper.UserMapper;
import com.emazon.api_user.application.util.ConstantsApplication;
import com.emazon.api_user.domain.api.IUserServicePort;
import com.emazon.api_user.domain.model.UserSave;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final UserMapper userMapper;

    @Override
    public ResponseSuccess saveUser(UserRequestDto userRequestDto, String role) {
        UserSave userSave = userMapper.userRequestDtoToUserSave(userRequestDto);
        userServicePort.saveUser(userSave,role);
        return new ResponseSuccess(ConstantsApplication.CREATE_USER);
    }
}
