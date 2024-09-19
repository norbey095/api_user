package com.emazon.api_user.infraestructure.util;

public class ConstantsInfraestructure {

    public static final String AUTHORIZATION = "Authorization";
    public static final String BEARER = "Bearer ";
    public static final Integer VALUE_7 = 7;
    public static final String JWT_SECRET = "${jwt.secret}";
    public static final String COMA = ",";
    public static final String AUTHORITIES = "authorities";
    public static final Integer VALUE_100 = 1000;
    public static final Integer VALUE_60 = 60;
    public static final Integer VALUE_24 = 24;
    public static final String USER_NOT_FOUND_POINTS = "User not found: ";
    public static final String LAST_NAME = "Last_name";
    public static final String DOCUMENT_NUMBER = "document_number";
    public static final String CELL_PHONE = "cell_phone";
    public static final String ID_ROL = "id_rol";
    public static final String ROLE = "ROLE_";
    public static final String ROL = "rol";
    public static final String APPLICATION_JSON = "application/json";
    public static final String TOKEN_INVALID = "Invalid token: signature does not match.";
    public static final String ROLE_AUX_WAREHOUSE = "AUX_WAREHOUSE";
    public static final String HAS_ADMIN = "hasRole('ROLE_ADMIN')";
    public static final String ROLE_CLIENT = "CLIENT";
    public static final String UTIL_CLASS = "Utility class";

    private ConstantsInfraestructure() {
    }
}