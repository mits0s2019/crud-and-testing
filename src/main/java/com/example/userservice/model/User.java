package com.example.userservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "user")
@XmlRootElement
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "iduser")
    private Integer userId;

    @Basic(optional = false)
    @NotNull
    @Size(min = 4, max = 30)
    @Column(name = "username")
    private String username;
    private String email;


    public User() {
    }

    public User(int id,String username,String email) {
        this.userId=id;
        this.username=username;
        this.email=email;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
