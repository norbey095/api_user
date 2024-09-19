package com.emazon.api_user.domain.spi;

import com.emazon.api_user.domain.model.UserAuth;


public interface IAthenticationPersistencePort {

     UserAuth authenticate(String email, String password);

     String getToken(UserAuth user);
}
