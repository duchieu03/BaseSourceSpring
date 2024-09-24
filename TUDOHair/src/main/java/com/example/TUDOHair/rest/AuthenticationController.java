package com.example.TUDOHair.rest;

import com.example.TUDOHair.base.ApiRes;
import com.example.TUDOHair.base.WebConstants;
import com.example.TUDOHair.dto.request.LoginRequest;
import com.example.TUDOHair.dto.request.RegisterRequest;
import com.example.TUDOHair.dto.response.LoginResponse;
import com.example.TUDOHair.service.AuthenticateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(WebConstants.API_AUTH_PREFIX_V1)
public class AuthenticationController {

    private final AuthenticateService authenticateService;

    @PostMapping("/login")
    public ResponseEntity<ApiRes<LoginResponse>> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(ApiRes.success(authenticateService.login(request)));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiRes<LoginResponse>> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(ApiRes.success(authenticateService.register(request)));
    }
}
