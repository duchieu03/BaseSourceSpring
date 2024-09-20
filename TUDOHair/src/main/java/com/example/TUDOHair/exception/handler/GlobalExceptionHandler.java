package com.example.TUDOHair.exception.handler;

import com.example.TUDOHair.base.ApiRes;
import com.example.TUDOHair.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiRes<Void>> handleApiException(ApiException e) {
        log.error("Error: ", e);
        return ResponseEntity.badRequest().body(ApiRes.with(e.getErrorCode()));
    }
}
