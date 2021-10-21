package com.mintic.api_mintic.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mintic.api_mintic.models.request.UserLoginRequestModel;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class FilterAuthentication extends UsernamePasswordAuthenticationFilter{

    private final AuthenticationManager authenticationManager;


    public FilterAuthentication(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        
        try {

            UserLoginRequestModel userLoginRequestModel = new ObjectMapper().readValue(
                request.getInputStream(),UserLoginRequestModel.class);

                return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                        userLoginRequestModel.getUserName(), 
                        userLoginRequestModel.getPassword(),
                        new ArrayList<>())
                );
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        String userName = ((User) authResult.getPrincipal()).getUsername();

        String token = Jwts.builder()
                        .setSubject(userName)
                        .setExpiration(new Date(System.currentTimeMillis() + ConstantsSecurity.EXPIRATION_DATE))
                        .signWith(SignatureAlgorithm.HS512, ConstantsSecurity.TOKEN_SECRET)
                        .compact();

        response.addHeader(ConstantsSecurity.HEADER_STRING, ConstantsSecurity.TOKEN_PREFIX + token);

    }

    
}
