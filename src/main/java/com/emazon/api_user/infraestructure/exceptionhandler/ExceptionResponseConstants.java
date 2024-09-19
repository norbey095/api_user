package com.emazon.api_user.infraestructure.exceptionhandler;

import lombok.Getter;

@Getter
public enum ExceptionResponseConstants {
    USER_ALREADY_EXISTS("There is already an user with that email"),
    USER_EMAIL_CORRECT("The email does not have the correct structure"),
    USER_PHONE_CORRECT("Invalid phone format"),
    MINOR_INVALID("The user must not be a minor"),
    ROL_AUX_BODE_EXISTS("The role aux_bodega does not exist in the system"),
    DOCUMENT_NUMBER_POSITIVE ("The ID document must be a positive number");

    private final String message;

    ExceptionResponseConstants(String message) {
        this.message = message;
    }

}
