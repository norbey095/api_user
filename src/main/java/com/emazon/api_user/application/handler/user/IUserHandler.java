package com.emazon.api_user.application.handler.user;

import com.emazon.api_user.application.dto.ResponseSuccess;
import com.emazon.api_user.application.dto.UserRequestDto;

public interface IUserHandler {

    ResponseSuccess saveUser(UserRequestDto userRequestDto, String role);

}
