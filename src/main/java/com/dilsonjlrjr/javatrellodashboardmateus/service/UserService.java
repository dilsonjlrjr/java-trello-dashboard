package com.dilsonjlrjr.javatrellodashboardmateus.service;

import com.dilsonjlrjr.javatrellodashboardmateus.exception.ServiceException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumUserServiceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumUserServiceMessage;
import com.dilsonjlrjr.javatrellodashboardmateus.mapper.UserMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User getByUsername(String username) {
        return userMapper.findByUsername(username)
                .orElseThrow(() -> new ServiceException(
                        EnumUserServiceMessage.ENTITY_NOT_FOUND.getMessage(),
                        EnumUserServiceCode.ENTITY_NOT_FOUND.getCode()));
    }

    public void updateSessionUser(Long id, String hashSession, String refreshToken) {
        userMapper.updateSessionUser(id, hashSession, refreshToken);
    }

    public User getByUsername(String refreshToken, String hashSession) {
        return userMapper.findByRefreshTokenHashSession(refreshToken, hashSession)
                .orElseThrow(() -> new ServiceException(
                        EnumUserServiceMessage.ENTITY_NOT_FOUND.getMessage(),
                        EnumUserServiceCode.ENTITY_NOT_FOUND.getCode()));
    }
}
