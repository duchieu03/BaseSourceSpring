package com.example.TUDOHair.enums.code;

import com.example.TUDOHair.base.ApiCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AuthApiCode implements ApiCode{
    INVALID_JWT_SIGNATURE(40, "Invalid JWT signature"),
    INVALID_JWT_TOKEN(41, "Invalid JWT token"),
    JWT_TOKEN_EXPIRED(42, "JWT token expired"),
    UNSUPPORTED_JWT_TOKEN(43, "Unsupported JWT token"),
    JWT_CLAIMS_EMPTY(44, "JWT claims empty"),
    USER_ID_NOT_FOUND(45, "User not found"),
    PERMISSION_DENIED(46, "Permission denied"),
    NEED_AUTHENTICATION(47, "Need authentication"),
    EMAIL_OR_PASSWORD_IS_INCORRECT(48, "Credentials is incorrect"),
    NAME_IS_REQUIRED(49, "Name is required"),
    EMAIL_IS_REQUIRED(50, "Email is required"),
    INVALID_EMAIL(51, "Invalid email"),
    EMAIL_IS_IN_USED(52, "Email is in used")

    ;
    private final Integer code;
    private final String message;
}
