package com.example.TUDOHair.service.impl;

import com.example.TUDOHair.dto.Token;
import com.example.TUDOHair.dto.request.LoginRequest;
import com.example.TUDOHair.dto.request.RegisterRequest;
import com.example.TUDOHair.dto.response.LoginResponse;
import com.example.TUDOHair.entity.User;
import com.example.TUDOHair.enums.Role;
import com.example.TUDOHair.enums.code.AuthApiCode;
import com.example.TUDOHair.enums.code.CommonApiCode;
import com.example.TUDOHair.exception.ApiException;
import com.example.TUDOHair.repository.UserRepository;
import com.example.TUDOHair.service.AuthTokenService;
import com.example.TUDOHair.service.AuthenticateService;
import com.example.TUDOHair.utils.RegexUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticateServiceImpl implements AuthenticateService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AuthTokenService authTokenService;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ApiException(AuthApiCode.EMAIL_OR_PASSWORD_IS_INCORRECT));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user,
                        request.getPassword(),
                        user.getAuthorities()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return getLoginResponse(user);
    }

    @Override
    public LoginResponse register(RegisterRequest request) {
        if (!StringUtils.hasText(request.getName())) {
            throw new ApiException(AuthApiCode.NAME_IS_REQUIRED);
        }

        if (!StringUtils.hasText(request.getEmail())) {
            throw new ApiException(AuthApiCode.EMAIL_IS_REQUIRED);
        }

        if (!RegexUtils.EMAIL_PATTERN_V2.matcher(request.getEmail()).find()) {
            throw new ApiException(AuthApiCode.INVALID_EMAIL);
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ApiException(AuthApiCode.EMAIL_IS_IN_USED);
        }

        User user = User.builder()
                .role(Role.USER)
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepository.save(user);
        return getLoginResponse(user);
    }

    public LoginResponse getLoginResponse(User user) {
        try {
            Token token = authTokenService.createRefreshToken(user.getId());
            String accessToken = authTokenService.createAccessToken(user);
            return LoginResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(token.getValue())
                    .refreshTokenExpiredAt(token.getExpiredAt())
                    .name(user.getName())
                    .email(user.getEmail())
                    .build();
        } catch (Exception e) {
            log.error("Error: ", e);
            throw new ApiException(CommonApiCode.ERROR);
        }
    }
}
