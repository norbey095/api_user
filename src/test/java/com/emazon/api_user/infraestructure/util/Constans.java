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


    private Constans() {

    }
}
