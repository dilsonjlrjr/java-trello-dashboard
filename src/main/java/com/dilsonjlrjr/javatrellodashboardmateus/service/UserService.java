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
                .password("e10adc3949ba59abbe56e057f20f883e")
                .build();
    }
}
