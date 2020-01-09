package com.example.userservice.controller;


import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerIT {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    public  void  ContextLoads() throws JSONException {
        String response=testRestTemplate.getForObject("/users",String.class);
        JSONAssert.assertEquals("[{userId:1,username:STERGIOS,email:das@das.com},{}]",response,false);
    }
}
