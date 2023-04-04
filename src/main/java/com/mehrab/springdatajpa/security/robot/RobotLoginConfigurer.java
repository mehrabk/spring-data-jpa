package com.mehrab.springdatajpa.security.robot;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

public class RobotLoginConfigurer extends AbstractHttpConfigurer<RobotLoginConfigurer, HttpSecurity> {


    @Override
    public void init(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authenticationProvider(new RobotAuthenticationProvider());
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
        httpSecurity.addFilterBefore(new RobotAuthenticationFilter(authenticationManager), AuthorizationFilter.class);
    }
}
