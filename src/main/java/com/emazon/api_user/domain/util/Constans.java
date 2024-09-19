package com.emazon.api_user.domain.util;

import java.util.regex.Pattern;

public class Constans {
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    public static final String PHONE_REGEX = "^\\+?\\d{1,12}$";
    public static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
    public static final Integer VALUE_18 = 18;
    public static final String AUX_BODEGA = "aux_bodega";
    public static final String REGEX_DOCUMENT = "\\d+";

    private Constans() {
    }
}
