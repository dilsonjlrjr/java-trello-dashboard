package com.dilsonjlrjr.javatrellodashboardmateus.service;

import com.dilsonjlrjr.javatrellodashboardmateus.exception.EntityNotFoundException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumUserServiceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumUserServiceMessage;
import com.dilsonjlrjr.javatrellodashboardmateus.util.DatabaseOrderUtils;
import com.dilsonjlrjr.javatrellodashboardmateus.mapper.UserMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper.ProjectDtoMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper.UserDtoMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.UserDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Project;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.User;
import com.github.pagehelper.PageInfo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.pagehelper.page.PageMethod.startPage;

@Service
public class UserService {

    private final UserMapper userMapper;

    private final DatabaseOrderUtils databaseOrderUtils;

    private final ProjectService projectService;

    @Autowired
    public UserService(UserMapper userMapper,
                       DatabaseOrderUtils databaseOrderUtils,
                       ProjectService projectService) {
        this.userMapper = userMapper;
        this.databaseOrderUtils = databaseOrderUtils;
        this.projectService = projectService;
    }

    public List<User> getAll() {
        return userMapper.findAll();
    }

    public User getByUsername(String username) {
        return userMapper.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException(
                        EnumUserServiceMessage.ENTITY_NOT_FOUND.getMessage(),
                        EnumUserServiceCode.ENTITY_NOT_FOUND.getCode()));
    }

    public void updateSessionUser(Long id, String hashSession, String refreshToken) {
        userMapper.updateSessionUser(id, hashSession, refreshToken);
    }

    public User getById(Long id) {
        return userMapper.findById(id).orElseThrow(() -> new EntityNotFoundException(
                EnumUserServiceMessage.ENTITY_NOT_FOUND.getMessage(),
                EnumUserServiceCode.ENTITY_NOT_FOUND.getCode()));
    }

    public User getByUsername(String refreshToken, String hashSession) {
        return userMapper.findByRefreshTokenHashSession(refreshToken, hashSession)
                .orElseThrow(() -> new EntityNotFoundException(
                        EnumUserServiceMessage.ENTITY_NOT_FOUND.getMessage(),
                        EnumUserServiceCode.ENTITY_NOT_FOUND.getCode()));
    }

    public PageInfo<UserDtoResponse> doFindAllUserAndCreateDto(Pageable pageable) {
        String orderByDatabase = databaseOrderUtils.doCreateStringOrdebyDatabase(pageable, User.class);
        startPage(pageable.getPageNumber(), pageable.getPageSize(), orderByDatabase);

        List<User> users = this.getAll();

        return new PageInfo<>(users.parallelStream().map(Mappers.getMapper(UserDtoMapper.class)::userToUserDtoResponse)
                .collect(Collectors.toList()));
    }

    public UserDtoResponse doFindByIdAndCreateDto(Long id) {
        return Mappers.getMapper(UserDtoMapper.class).userToUserDtoResponse(this.getById(id));
    }

    public PageInfo<ProjectDtoResponse> doFindProjectUserAndCreateDto(Pageable pageable, Long id) {
        String orderByDatabase = databaseOrderUtils.doCreateStringOrdebyDatabase(pageable, Project.class);
        startPage(pageable.getPageNumber(), pageable.getPageSize(), orderByDatabase);

        List<Project> projects = projectService.getByUserId(id);

        return new PageInfo<>(projects.parallelStream().map(Mappers.getMapper(ProjectDtoMapper.class)::projectToProjectDtoResponse)
                .collect(Collectors.toList()));
    }
}
