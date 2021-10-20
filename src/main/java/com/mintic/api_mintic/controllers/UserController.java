package com.mintic.api_mintic.controllers;

import com.mintic.api_mintic.models.request.UserRegisterRequestModel;
import com.mintic.api_mintic.models.responses.UserRestModel;
import com.mintic.api_mintic.shared.UserCreateDto;
import com.mintic.api_mintic.shared.UserDto;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/candidatos")
public class UserController {

    @Autowired
    ModelMapper modelmapper;

    @GetMapping
    public String getUser(){
        
        return "get user";
    }

    @PostMapping
    public UserRestModel createUser(@RequestBody UserRegisterRequestModel UserRegisterRequestModel){

        UserCreateDto  UserCreateDto = modelmapper.map(UserRegisterRequestModel,UserCreateDto.class);

        UserDto userDto;
        UserRestModel userRestModel = modelmapper.map(userDto, UserRestModel.class);

        return userRestModel;
    }
}

