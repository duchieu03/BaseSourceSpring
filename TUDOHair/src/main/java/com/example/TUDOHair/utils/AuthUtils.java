package com.example.TUDOHair.utils;

import com.example.TUDOHair.base.ApiCode;
import com.example.TUDOHair.base.ApiRes;
import com.example.TUDOHair.entity.User;
import com.example.TUDOHair.enums.Role;
import com.example.TUDOHair.enums.code.AuthApiCode;
import com.example.TUDOHair.exception.ApiException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import java.io.IOException;

@UtilityClass
@Slf4j
public class AuthUtils {
    public static String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static Role currentRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User user) {
            return user.getRole();
        }
        return null;
    }

    public static Long currentUserId() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User userPrincipal = (User) authentication.getPrincipal();
            return userPrincipal.getId();
        } catch (Exception e) {
            log.error("exception ", e);
            throw new ApiException(AuthApiCode.USER_ID_NOT_FOUND);
        }
    }

    public static void withUnauthorizedResponse(HttpServletResponse response, ApiCode code) throws IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println(SerializeUtils.toJson(ApiRes.with(code)));
    }
}
