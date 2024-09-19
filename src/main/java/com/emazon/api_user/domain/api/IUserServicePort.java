package com.emazon.api_user.domain.api;

import com.emazon.api_user.domain.model.UserSave;

public interface IUserServicePort {

      void saveUser(UserSave userSave,String role);

}
