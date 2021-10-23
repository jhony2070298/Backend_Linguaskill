package com.mintic.api_mintic.security;

import com.mintic.api_mintic.services.IUserService;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{


    private final IUserService iUsuarioService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public WebSecurity(IUserService iUsuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.iUsuarioService = iUsuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
        .and().csrf().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST,"/candidatos").permitAll()
        .anyRequest().authenticated();
    
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(iUsuarioService).passwordEncoder(bCryptPasswordEncoder);
    }
    
    
}

