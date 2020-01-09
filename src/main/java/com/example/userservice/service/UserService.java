package com.example.userservice.service;

import com.example.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

     List<User> findAll();

    public Optional<User> create(User user);
}
