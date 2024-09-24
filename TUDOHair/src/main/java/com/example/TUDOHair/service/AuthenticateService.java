package com.example.TUDOHair.service;

import com.example.TUDOHair.dto.request.LoginRequest;
import com.example.TUDOHair.dto.request.RegisterRequest;
import com.example.TUDOHair.dto.response.LoginResponse;

public interface AuthenticateService {
    LoginResponse login(LoginRequest request);

    LoginResponse register(RegisterRequest request);
}
