package com.emazon.api_user.domain.usecase;

import com.emazon.api_user.domain.api.IAuthenticationServicePort;
import com.emazon.api_user.domain.exception.CredentialsException;
import com.emazon.api_user.domain.model.UserAuth;
import com.emazon.api_user.domain.spi.IAthenticationPersistencePort;

public class AuthenticationUseCase implements IAuthenticationServicePort {

    private final IAthenticationPersistencePort athenticationPersistencePort;

    public AuthenticationUseCase(IAthenticationPersistencePort athenticationPersistencePort) {
        this.athenticationPersistencePort = athenticationPersistencePort;
    }

    @Override
    public String authentication(String email, String password) {
        UserAuth user = athenticationPersistencePort.authenticate(email,
                password);
        if(user == null){
            throw new CredentialsException();
        }
        return athenticationPersistencePort.getToken(user);
    }


}