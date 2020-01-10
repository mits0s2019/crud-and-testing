package com.example.userservice.controller;

import com.example.userservice.exception.UserNotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
        User createdUser=userService.create(user);
        URI uri= ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{userId}").buildAndExpand(createdUser.getUserId()).toUri();

        return ResponseEntity.created(uri).body(createdUser);
    }

    @PutMapping("user/{id}")
    public ResponseEntity update(@PathVariable int id, @Valid @RequestBody User user, WebRequest request) {
        try {
            User userPreUpdate = userService.findUserById(id).orElseThrow(() -> new UserNotFoundException());
            if (userPreUpdate.getUserId()==user.getUserId()){
                return ResponseEntity.ok(userService.create(user));
            }
        }catch(Exception e){
            logger.error(request.getDescription(false));
            logger.error(e.getMessage(),User.class);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("user/{id}")
    void deleteEmployee(@PathVariable int id) {
        userService.deleteUserById(id);
    }
}
