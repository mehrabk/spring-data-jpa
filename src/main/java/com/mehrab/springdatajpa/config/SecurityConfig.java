package com.mehrab.springdatajpa.config;

import com.mehrab.springdatajpa.security.person.UserPwdConfigurer;
import com.mehrab.springdatajpa.security.robot.RobotLoginConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationEventPublisher;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationEventPublisher authzPublisher) throws Exception {

        return http
                .authorizeHttpRequests((authz) -> {
                    authz.requestMatchers("/").permitAll();
                    authz.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults())
//                .apply(new UserPwdConfigurer())
                .apply(new RobotLoginConfigurer())
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
