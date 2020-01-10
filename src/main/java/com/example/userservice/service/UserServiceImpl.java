package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersRepo usersRepo;

    @Override
    public List<User> findAll() {
      return usersRepo.findAll().stream().map((x)->{
          x.setUsername(x.getUsername().toUpperCase());
           return x;
       }).collect(Collectors.toList());

    }

    @Override
    public List<User> findAllUsers() {
        return usersRepo.findAll();
    }

    @Override
    public User create(User user) {
       return usersRepo.save(user);
    }

    @Override
    public Optional<User> findUserById(int id) {
        return usersRepo.findById(id);
    }

    @Override
    public void deleteUserById(int id) {
        usersRepo.deleteById(id);
    }


}
