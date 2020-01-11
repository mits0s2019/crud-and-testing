package com.example.userservice.controller;


import com.example.userservice.model.User;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    int randomPort;

    @Value("${server.context-path}")
    String serverPath;

    @Test
    public  void  contextLoadsGetAllUsers() throws JSONException {
        String response=testRestTemplate.getForObject("/usersUpper",String.class);
        JSONAssert.assertEquals("[{userId:1,username:STERGIOS,email:das@das.com},{}]",response,false);
    }

    @Test
    public  void  contextLoadsPostUserCreated() throws JSONException, URISyntaxException {
        User user = new User(1,"tester","tester@gmail.com");

        final String url = serverPath+":"+randomPort+"/user";
        URI uri = new URI(url);

        HttpEntity<User> request = new HttpEntity<>(user);

        ResponseEntity<String> response=testRestTemplate.postForEntity(uri,request,String.class);

        assertThat(response.getStatusCodeValue()).isEqualTo(201);
    }
}
