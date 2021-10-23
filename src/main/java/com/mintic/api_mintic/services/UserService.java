package com.mintic.api_mintic.services;

import java.util.UUID;

import com.mintic.api_mintic.data.entities.UserEntity;
import com.mintic.api_mintic.data.repos.IUserRepository;
import com.mintic.api_mintic.shared.UserCreateDto;
import com.mintic.api_mintic.shared.UserDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

     @Autowired
     BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IUserRepository iUsuarioRepository;

    @Override
    public UserDto crearUsuario(UserCreateDto usuarioCrearDto) {

        if(iUsuarioRepository.findByCorreo(usuarioCrearDto.getCorreo()) != null){
            throw new RuntimeException("Este correo ya se encuentra registrado");
        }

        if(iUsuarioRepository.findByUserName(usuarioCrearDto.getUserName()) != null){
            throw new RuntimeException("Este nombre de usuario ya está en uso");
        }

        UserEntity usuarioEntityDto = modelMapper.map(usuarioCrearDto, UserEntity.class);
        usuarioEntityDto.setUserId(UUID.randomUUID().toString());
        usuarioEntityDto.setEncryptedPassword(bCryptPasswordEncoder.encode(usuarioCrearDto.getPassword()));

        UserEntity usuarioEntitySave = iUsuarioRepository.save(usuarioEntityDto);

        UserDto usuarioDto = modelMapper.map(usuarioEntitySave, UserDto.class);
        
        return usuarioDto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return null;
    }
    

}
