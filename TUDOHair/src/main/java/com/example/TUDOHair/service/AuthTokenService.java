package com.example.TUDOHair.service;


import com.example.TUDOHair.dto.Token;
import com.example.TUDOHair.entity.User;

import java.time.Instant;

public interface AuthTokenService {

    Token createRefreshToken(Long userId);

    long getExpireDate(Instant expiredAt);

    String createAccessToken(User user);

    String createAccessToken(User user, long expireMils);

}
