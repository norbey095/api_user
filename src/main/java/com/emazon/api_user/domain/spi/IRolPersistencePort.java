package com.emazon.api_user.domain.spi;


import com.emazon.api_user.domain.model.RolSave;

public interface IRolPersistencePort {

     RolSave getRolByName(String name);
}

