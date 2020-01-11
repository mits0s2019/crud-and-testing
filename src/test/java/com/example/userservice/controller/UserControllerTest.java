package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import com.example.userservice.utils.JSONUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @MockBean
    UserService userService;
    @Autowired
    MockMvc mockMvc;

    @Value("${server.context-path}")
    private String serverContextPath;

    @Test
    void getUsers() throws Exception {
        when(userService.findAll()).thenReturn(Arrays.asList(new User(1,"AAAA","das"),
                new User(2,"AAA","das")));
        RequestBuilder requestBuilder= MockMvcRequestBuilders.get("/usersUpper")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result=mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("[{userId:1,username:AAAA,email:das},{}]"))
                .andReturn();
    }

    @Test
    void postUserBasic() throws Exception {
        User user = new User(1,"tester","tester@gmail.com");
        when(userService.create(any(User.class))).thenReturn(user);

        RequestBuilder requestBuilder = buildRequestToUserPost(user);

         MvcResult result = mockMvc.perform(requestBuilder)
         .andExpect(status().isCreated())
         .andExpect(content().json("{userId:1,username=tester,email:tester@gmail.com}"))
         .andExpect(header().stringValues("Location", serverContextPath+"/user/1"))
                 .andReturn();
    }

    @Test
    void postUserNotValidEmail() throws Exception {

        User user = new User(1,"tester","tester@.com");
        when(userService.create(any(User.class))).thenReturn(user);

        RequestBuilder requestBuilder = buildRequestToUserPost(user);

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.details",is("Invalid email.")))
                .andExpect(jsonPath("$.message", is("Validation Failed")))
                .andDo(print())
                .andReturn();
    }


    private RequestBuilder buildRequestToUserPost(User user){
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user")
                .content(JSONUtils.asJsonString(user))
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON);

        return  requestBuilder;
    }


}