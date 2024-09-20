package com.example.TUDOHair.rest;

import com.example.TUDOHair.config.property.AuthProperty;
import com.example.TUDOHair.entity.User;
import com.example.TUDOHair.enums.Role;
import com.example.TUDOHair.utils.AuthTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final AuthProperty authProperty;

    @GetMapping("/test")
    public String test(){
        return "Hello World";
    }

    @GetMapping("/user")
    public String user(){
        return "Hello User";
    }

    @GetMapping("/staff")
    public String staff(){
        return "Hello Staff";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Hello Admin";
    }

    @GetMapping("/generate-jwt")
    public String generate(@RequestParam Role role){
        User user = User.builder()
                .email("duchieu03@gmail.com")
                .name("HieuND")
                .role(role)
                .build();

        return AuthTokenUtils.createAccessToken(1L, user,
                authProperty.getAccessTokenExpirationMils(), authProperty.getTokenSecret());
    }
}
