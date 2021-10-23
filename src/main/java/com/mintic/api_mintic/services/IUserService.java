package com.mintic.api_mintic.services;

import com.mintic.api_mintic.shared.UserCreateDto;
import com.mintic.api_mintic.shared.UserDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService extends UserDetailsService{

    public UserDto createUser(UserCreateDto userCreateDto);
    public UserDto getUser(String userName);


}