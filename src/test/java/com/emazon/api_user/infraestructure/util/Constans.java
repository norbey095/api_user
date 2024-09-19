package com.emazon.api_user.infraestructure.util;

import java.time.LocalDate;

public class Constans {

    public static final String URL_USER= "/user/registry";
    public static final String JSON_REQUEST= "{ \"name\": \"Constans\", \"lastName\": \"Constans\", " +
            "\"documentNumber\": \"123\", \"cellPhone\": \"3020302030\", \"birthdate\": \"2000-09-12\", " +
            "\"email\": \"teste@test.com\", \"password\": \"string\" }";
    public static final String JSON_REQUEST_INCORRECT= "{ \"name\": \"\", \"lastName\": \"Constans\", " +
            "\"documentNumber\": \"123\", \"cellPhone\": \"3020302030\", \"birthdate\": \"2000-09-12\", " +
            "\"email\": \"teste@test.com\", \"password\": \"string\" }";
    public static final String NAME_EMPTY = "The parameter 'name' cannot be empty";
    public static final String MESSAGE = "$.message";
    public static final String MESSAGE_EMAIL = "There is already an user with that email";
    public static final String  USER_EMAIL_CORRECT = "The email does not have the correct structure";
    public static final String USER_PHONE_CORRECT ="Invalid phone format";
    public static final String MINOR_INVALID ="The user must not be a minor";
    public static final String ROL_AUX_BODE_EXISTS ="The role aux_bodega does not exist in the system";
    public static final String DOCUMENT_NUMBER_POSITIVE ="The ID document must be a positive number";
    public static final String NAME = "Duvan";
    public static final String LAST_NAME = "Sanchez";
    public static final String DOCUMENT = "120142541";
    public static final String CELLPHONE = "3254557253";
    public static final LocalDate BIRTHDATE=  LocalDate.of(2000, 9, 4);
    public static final String EMAIL = "duvan123@gmail.com";
    public static final String PASSWORD= "1234";
    public static final String PASSWORD_ENCRYPT= "myPassword123";
    public static final Integer ROL_ID= 1;
    public static final String ROL_NAME= "Auxiliar";
    public static final String ROL_DESCRIPTION= "Auxiliar";
    public static final String MESSAGESS_SUCCESS = "User created successfully";
    public static final String USER_NAME = "testuser";
    public static final String ADMIN = "ADMIN";
    public static final String EMAIL_EJEM = "test@example.com";
    public static final String NAME_INVALID = "The extracted username is not the expected one.";
    public static final Integer VALUE_100 = 1000;
    public static final Integer VALUE_60 = 60;
    public static final String TOKEN_VALID = "Token válid.";
    public static final String TOKEN_INVALID = "Token Inválid.";
    public static final String ROL_NAME_AUXILIAR= "ROLE_Auxiliar";
    public static final String URL_AUTHENTICATION= "/auth/login";
    public static final String AUTHORIZATION= "authorization";
    public static final String JWTTOKEN = "jwtToken";
    public static final String PASSWORD_NAME = "password";
    public static final String INAUTHORIZATION= "Incorrect login information";
    public static final String DATE_TIME= "The date format is invalid, the format is yyyy-mm-dd";
    public static final String INPUT= "INPUT";
    public static final Integer VALUE_0= 0;
    public static final String USER_NOT_FOUND_POINTS = "User not found: ";
    public static final Integer VALUE_1= 1;


    private Constans() {

    }
}
