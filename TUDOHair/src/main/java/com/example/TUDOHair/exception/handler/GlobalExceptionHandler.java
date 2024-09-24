package com.example.TUDOHair.exception.handler;

import com.example.TUDOHair.base.ApiRes;
import com.example.TUDOHair.enums.code.AuthApiCode;
import com.example.TUDOHair.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
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

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiRes<Void>> handleAccessDeniedException(AccessDeniedException e) {
        log.error("Error: ", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiRes.with(AuthApiCode.PERMISSION_DENIED));
    }

    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ApiRes<Void>> handleInsufficientAuthenticationException(InsufficientAuthenticationException e) {
        log.error("Error: ", e);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ApiRes.with(AuthApiCode.NEED_AUTHENTICATION));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiRes<Void>> handleBadCredentialsException(BadCredentialsException e) {
        log.error("Error: ", e);
        return ResponseEntity.badRequest().body(ApiRes.with(AuthApiCode.EMAIL_OR_PASSWORD_IS_INCORRECT));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiRes<Void>> handleException(Exception e) {
        log.error("Error: ", e);
        return ResponseEntity.badRequest().body(ApiRes.error(e.getMessage()));
    }
}
