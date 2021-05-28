package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.UserDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserDtoMapper {
    UserDtoResponse userToUserDtoResponse(User user);
}
