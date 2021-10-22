package com.mintic.api_mintic.security;

import com.mintic.api_mintic.services.IUserService;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

    private final IUserService iUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public WebSecurity(IUserService iUserService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.iUserService = iUserService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        .authorizeRequests().antMatchers(HttpMethod.POST, "/usuarios").permitAll()
        .anyRequest().authenticated()
        .and().addFilter(getFilterAuthentication())
        .addFilter(new FilterAuthorization(authenticationManager()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(iUserService).passwordEncoder(bCryptPasswordEncoder);

    }

    public FilterAuthentication getFilterAuthentication() throws Exception{

        final FilterAuthentication filterAuthentication = new FilterAuthentication(authenticationManager());

        filterAuthentication.setFilterProcessesUrl("/usuarios/login");

        return filterAuthentication;
    }
}
