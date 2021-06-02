package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.SprintDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Sprint;
import org.mapstruct.Mapper;

@Mapper
public interface SprintDtoMapper {

    SprintDtoResponse sprintToSprintDtoResponse(Sprint sprint);
}
