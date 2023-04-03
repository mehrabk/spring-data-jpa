package com.mehrab.springdatajpa.security.person;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;

public class UserPwdAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("hello from UsernamePasswordAuthenticationProvider");
        if("mehrab".equals(authentication.getName())) {
            return UsernamePasswordAuthenticationToken.authenticated("mehrab",null, AuthorityUtils.createAuthorityList("ROLE_admin"));
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
