package com.mintic.api_mintic.services;

import com.mintic.api_mintic.shared.UserCreateDto;
import com.mintic.api_mintic.shared.UserDto;

public interface IUserService{

    public UserDto createUser(UserCreateDto userCreateDto);


}