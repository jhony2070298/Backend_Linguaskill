package com.mintic.api_mintic.controllers;

import com.mintic.api_mintic.models.request.UserRegisterRequestModel;
import com.mintic.api_mintic.models.responses.UserRestModel;
import com.mintic.api_mintic.services.IUserService;
import com.mintic.api_mintic.shared.UserCreateDto;
import com.mintic.api_mintic.shared.UserDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuarios")
public class UserController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IUserService iUserService;

    @GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public UserRestModel getUser(){
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        String userName = authentication.getPrincipal().toString();

        UserDto userDto = iUserService.getUser(userName);

        UserRestModel userRestModel = modelMapper.map(userDto,UserRestModel.class);

        return userRestModel;
    }

    @PostMapping
    public UserRestModel createUser(@RequestBody UserRegisterRequestModel UserRegisterRequestModel){

        UserCreateDto  UserCreateDto = modelMapper.map(UserRegisterRequestModel,UserCreateDto.class);

        UserDto userDto = iUserService.createUser(UserCreateDto);
        
        UserRestModel userRestModel = modelMapper.map(userDto, UserRestModel.class);

        return userRestModel;
    }
}

