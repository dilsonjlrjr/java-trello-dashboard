package com.dilsonjlrjr.javatrellodashboardmateus.service;

import com.dilsonjlrjr.javatrellodashboardmateus.exception.EntityNotFoundException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumSprintServiceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumUserServiceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumSprintServiceMessage;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumUserServiceMessage;
import com.dilsonjlrjr.javatrellodashboardmateus.mapper.SprintMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper.SprintDtoMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.SprintDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Sprint;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SprintService {

    private final SprintMapper sprintMapper;

    @Autowired
    public SprintService(SprintMapper sprintMapper) {
        this.sprintMapper = sprintMapper;
    }

    public List<Sprint> getAll(Long idProject) {
        return sprintMapper.getAll(idProject);
    }

    public Sprint getById(Long idProject, Long idSprint) {
        return sprintMapper.getById(idProject, idSprint).orElseThrow(() -> new EntityNotFoundException(
                EnumSprintServiceMessage.ENTITY_NOT_FOUND.getMessage(),
                EnumSprintServiceCode.ENTITY_NOT_FOUND.getCode()));
    }

    public Long doCreateSprintAndSave(SprintDtoRequest sprintDtoRequest, Long idProject) {
        Sprint sprint = Mappers.getMapper(SprintDtoMapper.class).sprintDtoRequestToSprint(sprintDtoRequest, idProject);

        sprintMapper.save(sprint);
        return sprint.getId();
    }
}
