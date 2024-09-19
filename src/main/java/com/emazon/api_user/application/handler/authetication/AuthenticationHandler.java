package com.emazon.api_user.application.handler.authetication;

import com.emazon.api_user.application.dto.authentication.AuthenticationRequestDto;
import com.emazon.api_user.domain.api.IAuthenticationServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationHandler implements IAuthenticationHandler {

    private final IAuthenticationServicePort userServicePort;

    @Override
    public String authentication(AuthenticationRequestDto authenticationRequestDto) {
        return userServicePort.authentication(authenticationRequestDto.getEmail(),
                authenticationRequestDto.getPassword());
    }
}