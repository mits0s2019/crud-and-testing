package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;

    @Test
    void getUsers() throws Exception {
        when(userService.findAll()).thenReturn(Arrays.asList(new User(1,"AAAA","das"),
                new User(2,"AAA","das")));
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/users")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("[{userId:1,username:AAAA,email:das},{}]"))
                .andReturn();
    }
}