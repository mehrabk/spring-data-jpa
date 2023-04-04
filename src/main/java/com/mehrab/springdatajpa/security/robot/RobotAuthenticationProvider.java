package com.mehrab.springdatajpa.security.robot;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.List;

public class RobotAuthenticationProvider  implements AuthenticationProvider {

    private final List<String> validPasswords = List.of("mehrab123", "aram123");

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        RobotAuthentication authRequest = (RobotAuthentication) authentication;

        if (validPasswords.contains(authRequest.getPassword())) {
            return RobotAuthentication.authenticated();
        }
        throw new BadCredentialsException("You are not Ms Robot 🤖");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return RobotAuthentication.class.isAssignableFrom(authentication);
    }
}
