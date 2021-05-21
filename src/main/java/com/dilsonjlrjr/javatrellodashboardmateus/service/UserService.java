package com.dilsonjlrjr.javatrellodashboardmateus.service;

import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getByUsername(String username) {
        return User.builder()
                .name("Dilson José")
                .id(1L)
                .username("dilsonjlrjr@gmail.com")
                .password("$2a$10$r.fqpqcVZ5ROXNP8pTdQeexAO0kUUm3pXk/OP203pBKi2nHMOoUVW")
                .hashSession("e10adc3949ba59abbe56e057f20f883e")
                .build();
    }
}
