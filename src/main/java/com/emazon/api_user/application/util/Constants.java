package com.emazon.api_user.application.util;

import lombok.Getter;

@Getter
public class Constants {
    public static final String NAME_REQUIRED = "The parameter 'name' cannot be empty";
    public static final String LAST_NAME_REQUIRED = "The parameter 'lastName' cannot be empty";
    public static final String DOCUMENT_REQUIRED = "The parameter 'documentNumber' cannot be empty";
    public static final String CELLPHONE_REQUIRED = "The parameter 'cellPhone' cannot be empty";
    public static final String BIRTHDATE_REQUIRED = "The parameter 'birthdate' cannot be empty";
    public static final String EMAIL_REQUIRED = "The parameter 'email' cannot be empty";
    public static final String PASSWORD_REQUIRED = "The parameter 'password' cannot be empty";
    public static final String CREATE_USER = "User created successfully";

    private Constants() {

    }
}
