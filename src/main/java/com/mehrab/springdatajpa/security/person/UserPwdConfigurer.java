package com.mehrab.springdatajpa.security.person;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class UserPwdConfigurer extends AbstractHttpConfigurer<UserPwdConfigurer, HttpSecurity> {

    @Override
    public void init(HttpSecurity http) throws Exception {
        http.authenticationProvider(new UserPwdAuthenticationProvider());
        super.init(http);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilterBefore(new UserPwdFilter(authenticationManager), UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }
}
