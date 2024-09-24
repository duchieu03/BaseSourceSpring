package com.example.TUDOHair.service.impl;

import com.example.TUDOHair.config.property.AuthProperty;
import com.example.TUDOHair.dto.Token;
import com.example.TUDOHair.entity.User;
import com.example.TUDOHair.service.AuthTokenService;
import com.example.TUDOHair.utils.AuthTokenUtils;
import com.example.TUDOHair.utils.DateTimeUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthTokenServiceImpl implements AuthTokenService {

    private final AuthProperty authProperty;

    @Override
    public Token createRefreshToken(Long userId) {
        Instant expiredAt = DateTimeUtils.getCurrentInstantMilliseconds().plusMillis(
            authProperty.getRefreshTokenExpirationMils());
        String value = UUID.randomUUID().toString();
        Token token = Token.from(userId, value, expiredAt);
//        TokenContextHolder.setUserRefreshToken(token);
        return token;
    }

    @Override
    public long getExpireDate(Instant expiredAt) {
        Instant maxExpiryDate = DateTimeUtils.plusMils(DateTimeUtils.now(), authProperty.getAccessTokenExpirationMils());
        if (expiredAt.isBefore(maxExpiryDate)) {
            return DateTimeUtils.milsBetween(DateTimeUtils.now(), expiredAt);
        } else {
            return authProperty.getAccessTokenExpirationMils();
        }
    }

    @Override
    public String createAccessToken(User user) {
        String value = AuthTokenUtils.createAccessToken(
            user.getId(),
            User.getUserInfo(user),
            authProperty.getAccessTokenExpirationMils(),
            authProperty.getTokenSecret());
        Instant expiredAt = DateTimeUtils.getCurrentInstantMilliseconds().plusMillis(
            authProperty.getAccessTokenExpirationMils());
        Token token = Token.from(user.getId(), value, expiredAt);
//        TokenContextHolder.setUserAccessToken(token);
        return value;
    }

    @Override
    public String createAccessToken(User user, long expireMils) {
        String value = AuthTokenUtils.createAccessToken(
            user.getId(),
            User.getUserInfo(user),
            expireMils,
            authProperty.getTokenSecret());
        Instant expiredAt = DateTimeUtils.getCurrentInstantMilliseconds().plusMillis(
            expireMils);
        Token token = Token.from(user.getId(), value, expiredAt);
//        TokenContextHolder.setUserAccessToken(token);
        return value;
    }

}
