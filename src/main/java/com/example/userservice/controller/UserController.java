package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.repository.UsersRepo;
import com.example.userservice.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("usersUpper")
    public Iterable<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("users")
    public ResponseEntity getAllUsers(){

        return ResponseEntity.ok(userService.findAllUsers());
    }

    @PostMapping("user")
    public ResponseEntity save(@Valid @RequestBody User user){
        Optional<User> createdUser=userService.create(user);
        createdUser.orElseThrow(()->new RuntimeException());
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{userId}").buildAndExpand(createdUser.get().getUserId()).toUri();
         return ResponseEntity.created(uri).body(createdUser);
    }

    @PutMapping("user/{id}")
    public ResponseEntity update(@PathVariable int id,@Valid @RequestBody User user) {

        User userPreUpdate=userService.findUserById(id).orElseThrow(()->new RuntimeException());
        if (userPreUpdate.getUserId()==user.getUserId()){
             return ResponseEntity.ok(userService.create(user));
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("user/{id}")
    void deleteEmployee(@PathVariable int id) {
        userService.deleteUserById(id);
    }
}
