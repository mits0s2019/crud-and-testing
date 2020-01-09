package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UsersRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UsersRepo usersRepo;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findAll() {
        when(usersRepo.findAll()).thenReturn(Arrays.asList(new User(1,"das","das"),
                new User(2,"aaa","das")));
        assertThat(userService.findAll()).hasSize(2)
                .allMatch((x)->x.getUsername().equals(x.getUsername().toUpperCase()));
    }
}