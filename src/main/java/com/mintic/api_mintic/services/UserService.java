package com.mintic.api_mintic.services;

import com.mintic.api_mintic.data.entities.UserEntity;
import com.mintic.api_mintic.data.repos.IUserRepository;
import com.mintic.api_mintic.shared.UserCreateDto;
import com.mintic.api_mintic.shared.UserDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IUserRepository iUserRepository;


    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {

        UserEntity userEntityDto = modelMapper.map(userCreateDto, UserEntity.class);
        userEntityDto.setUserId("userId");
        userEntityDto.setEcryptedPassword("encryptedPassword");

        UserEntity userEntitySave = iUserRepository.save(userEntityDto);

        UserDto userDto = modelMapper.map(userEntitySave, UserDto.class);

        return userDto;
    }
    
}