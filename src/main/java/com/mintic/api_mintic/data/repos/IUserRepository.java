package com.mintic.api_mintic.data.repos;

import com.mintic.api_mintic.data.entities.UserEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<UserEntity, Long> {
    
    public UserEntity findByCorreo(String correo);
    public UserEntity findByUserName(String userName);
}
