package com.example.TUDOHair.base;

import lombok.experimental.UtilityClass;

@UtilityClass
public class WebConstants {
    public static final String API_BASE_PREFIX_V1 = "/api/v1";

    public static final String API_AUTH_PREFIX_V1 = API_BASE_PREFIX_V1 + "/auth";
    public static final String API_FILE_PREFIX_V1 = API_BASE_PREFIX_V1 + "/file";

    public static final String UPLOADED_FILE_PREFIX = "/uploads";
}
