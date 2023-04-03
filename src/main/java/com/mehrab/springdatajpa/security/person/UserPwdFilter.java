package com.mehrab.springdatajpa.security.person;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class UserPwdFilter extends OncePerRequestFilter {

    // ProviderManager implemented this interface and run all ProviderManagers
    private final AuthenticationManager authenticationManager;

    public UserPwdFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
