package com.example.userservice.repository;

import com.example.userservice.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@DataJpaTest
class UsersRepoTest {

    @Autowired
    UsersRepo usersRepo;

    @Autowired
    TestEntityManager testEntityManager;

    @Test
    public void findAllTest(){
        List<User> userList=usersRepo.findAll();
        assertEquals(2,userList.size());
    }
}