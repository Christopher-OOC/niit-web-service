package com.javalord.niit_web_service.niit_web_service.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.*;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Map<String, String> loginDetails =
                    (Map<String, String>)objectMapper.readValue(request.getInputStream(), Map.class);

            System.out.println("Attempt method is called");

            System.out.println("EMAIL: " + loginDetails.get("email"));
            System.out.println("PASSWORD: " + loginDetails.get("password"));

            String email = loginDetails.get("email");
            String password = loginDetails.get("password");

            return getAuthenticationManager()
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            email,
                            password,
                            new ArrayList<>()
                    ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("Attempt method is called");

        super.successfulAuthentication(request, response, chain, authResult);
    }
}
