package com.example.userservice.service;

import com.example.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

     List<User> findAll();
     List<User> findAllUsers();
     Optional<User> create(User user);
     Optional<User> findUserById(int id);
     void deleteUserById(int id);
}
