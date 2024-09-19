package com.emazon.api_user.infraestructure.util;

public class ConstantsOutput {

    public static final String JWT_SECRET = "${jwt.secret}";
    public static final String AUTHORITIES = "authorities";
    public static final Integer EXPIRATION_TIME = 1000*60*24;
    public static final String LAST_NAME = "Last_name";
    public static final String DOCUMENT_NUMBER = "document_number";
    public static final String CELL_PHONE = "cell_phone";
    public static final String ID_ROL = "id_rol";
    public static final String ROLE = "ROLE_";
    public static final String ROL = "rol";

    private ConstantsOutput() {
    }
}