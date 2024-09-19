package com.emazon.api_user.application.handler.authetication;

import com.emazon.api_user.application.dto.authentication.AuthenticationRequestDto;

public interface IAuthenticationHandler {

    String authentication(AuthenticationRequestDto authenticationRequestDto);

}