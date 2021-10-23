package com.mintic.api_mintic.data.entities;

import java.io.Serializable;

// import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
// import javax.persistence.Index;
// import javax.persistence.OneToMany;
// import javax.persistence.Table;

@Entity(name="usuarios")
// @Table(indexes = {
//     @Index(columnList = "userId",name = "index_userid",unique = true),
//     @Index(columnList = "userName",name = "index_username",unique = true),
//     @Index(columnList = "correo",name = "index_correo",unique = true)
// })
public class UserEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedPassword() {
        return this.encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
    
    
}
