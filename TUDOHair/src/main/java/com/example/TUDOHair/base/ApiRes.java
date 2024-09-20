package com.example.TUDOHair.base;

import com.example.TUDOHair.enums.code.CommonApiCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ApiRes<T> {
    private int code;

    private String message;

    private T data;

    public static <T> ApiRes<T> with(ApiCode apiCode, T data){
        return ApiRes
                .<T>builder()
                .code(apiCode.getCode())
                .message(apiCode.getMessage())
                .data(data)
                .build();
    }

    public static <T> ApiRes<T> with(ApiCode apiCode) {
        return ApiRes
                .<T>builder()
                .code(apiCode.getCode())
                .message(apiCode.getMessage())
                .data(null)
                .build();
    }

    public static <T> ApiRes<T> success(T data) {
        return ApiRes.with(CommonApiCode.SUCCESS, data);
    }

    public static <T> ApiRes<T> error(String msg) {
        return ApiRes
                .<T>builder()
                .code(CommonApiCode.ERROR.getCode())
                .message(msg)
                .build();
    }
}
