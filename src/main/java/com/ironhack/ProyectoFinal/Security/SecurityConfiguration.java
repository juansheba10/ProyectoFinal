package com.ironhack.ProyectoFinal.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalAuthentication
public class SecurityConfiguration {


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception{
        return authConf.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.httpBasic();


        httpSecurity.authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/accounts/admin**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/accounts/admin**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PATCH, "/accounts/admin**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/accounts/admin**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET, "/admin**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/admin**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/admin**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/user**").hasRole("ACCOUNT_HOLDER")
                .requestMatchers(HttpMethod.POST,"/user**").hasRole("ACCOUNT_HOLDER")
                .requestMatchers(HttpMethod.PATCH,"/user**").hasRole("ACCOUNT_HOLDER")
                .requestMatchers(HttpMethod.GET, "/thirdparty**").hasRole("THIRD_PARTY")
                .requestMatchers(HttpMethod.POST, "/thirdparty**").hasRole("THIRD_PARTY")
                .anyRequest().permitAll();
        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }
}
