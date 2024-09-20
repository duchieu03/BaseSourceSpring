package com.example.TUDOHair.enums.code;

import com.example.TUDOHair.base.ApiCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CommonApiCode implements ApiCode {
    SUCCESS(200, "Success"),
    ERROR(500, "Error"),
    INVALID_PAYLOAD(400, "Invalid Payload"),
    NO_MERCHANT_AVAILABLE(402, "No merchant available"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not found record"),
    INVALID_QUERY(405, "Invalid sql query"),
    DUPLICATED_RECORD(407, "Duplicated record"),
    ;

    private final Integer code;
    private final String message;
}
