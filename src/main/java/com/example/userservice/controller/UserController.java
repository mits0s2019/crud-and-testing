package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.repository.UsersRepo;
import com.example.userservice.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("users")
    public Iterable<User> getUsers(){

        return userService.findAll();
    }

    @PostMapping("user")
    public ResponseEntity postUser(@RequestBody User user){
        Optional<User> createdUser=userService.create(user);
       createdUser.orElseThrow(()->new RuntimeException());
       URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{userId}").buildAndExpand(createdUser.get().getUserId()).toUri();
         return ResponseEntity.created(uri).body(createdUser);
    }
}
