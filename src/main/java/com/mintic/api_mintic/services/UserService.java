package com.mintic.api_mintic.services;

import java.util.ArrayList;
import java.util.UUID;

import com.mintic.api_mintic.data.entities.UserEntity;
import com.mintic.api_mintic.data.repos.IUserRepository;
import com.mintic.api_mintic.shared.UserCreateDto;
import com.mintic.api_mintic.shared.UserDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {

        if(iUserRepository.findByCorreo(userCreateDto.getCorreo()) != null){
            throw new RuntimeException("Este correo ya se encuentra registrado");

        }

        if(iUserRepository.findByUserName(userCreateDto.getUserName()) != null){
            throw new RuntimeException("El nombre de usuario ya esta en uso");

        }

        UserEntity userEntityDto = modelMapper.map(userCreateDto, UserEntity.class);
        userEntityDto.setUserId(UUID.randomUUID().toString());
        userEntityDto.setEcryptedPassword(bCryptPasswordEncoder.encode(userCreateDto.getPassword()));

        UserEntity userEntitySave = iUserRepository.save(userEntityDto);

        UserDto userDto = modelMapper.map(userEntitySave, UserDto.class);

        return userDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = iUserRepository.findByUserName(username);

        if(userEntity == null){
            throw new UsernameNotFoundException(username);
        }
        
        return new User(userEntity.getUserName(),userEntity.getEcryptedPassword(),new ArrayList<>());
    }

    @Override
    public UserDto getUser(String userName) {

        UserEntity userEntity = iUserRepository.findByUserName(userName);

        if(userEntity == null){
            throw new UsernameNotFoundException(userName);
        }

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);

        
        return userDto;
    }
    
}