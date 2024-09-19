package com.emazon.api_user.domain.spi;

import com.emazon.api_user.domain.model.UserSave;


public interface IUserPersistencePort {

    void saveUser(UserSave userSave);

    boolean getUserByEmail(String email);

    String encryptedPassword(String password);
}

