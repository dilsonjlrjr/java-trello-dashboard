package com.dilsonjlrjr.javatrellodashboardmateus.service;

import com.dilsonjlrjr.javatrellodashboardmateus.exception.EntityNotFoundException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.SecurityResourceException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumProjectServiceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumSecurityResourceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumProjectServiceMessage;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumSecurityResourceMessage;
import com.dilsonjlrjr.javatrellodashboardmateus.mapper.ProjectMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper.ProjectDtoMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.ProjectDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Project;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public List<Project> getByUserId(Long id) {
        return projectMapper.findByUserId(id);
    }

    public Project getById(Long id) {
        return projectMapper.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        EnumProjectServiceMessage.ENTITY_NOT_FOUND.getMessage(),
                        EnumProjectServiceCode.ENTITY_NOT_FOUND.getCode()));
    }

    public Long save(Project project) {
        projectMapper.save(project);
        return project.getId();
    }

    public Long doCreateProjectAndSave(Long idUsername, ProjectDtoRequest projectDtoRequest) {
        Project project = Mappers.getMapper(ProjectDtoMapper.class).projectDtoRequestToProject(projectDtoRequest, idUsername);
        return save(project);
    }

    public ProjectDtoResponse doFindProjectAndCreateDto(Long id, Long idUsername) {
        Project project = getById(id);

        if (!project.getOwner().getId().equals(idUsername))
            throw new SecurityResourceException(EnumSecurityResourceMessage.UNAUTHORIZED_ACCESS_RESOURCE.getMessage(),
                    EnumSecurityResourceCode.UNAUTHORIZED_ACCESS_RESOURCE.getCode());
        return Mappers.getMapper(ProjectDtoMapper.class).projectToProjectDtoResponse(project);
    }

    public void doCreateProjectAndUpdate(ProjectDtoRequest projectDtoRequest, Long idProject, Long idUsername) {
        Project projectWillUpdate = getById(idProject);

        if (!projectWillUpdate.getOwner().getId().equals(idUsername))
            throw new SecurityResourceException(EnumSecurityResourceMessage.UNAUTHORIZED_ACCESS_RESOURCE.getMessage(),
                    EnumSecurityResourceCode.UNAUTHORIZED_ACCESS_RESOURCE.getCode());

        Project project = Mappers.getMapper(ProjectDtoMapper.class).projectUpdateToProject(projectWillUpdate, projectDtoRequest);

        update(project);
    }

    private void update(Project project) {
        projectMapper.update(project);
    }

    public void doFindProjectAndDelete(Long idProject, Long idUsername) {
        Project project = getById(idProject);

        if (!project.getOwner().getId().equals(idUsername))
            throw new SecurityResourceException(EnumSecurityResourceMessage.UNAUTHORIZED_ACCESS_RESOURCE.getMessage(),
                    EnumSecurityResourceCode.UNAUTHORIZED_ACCESS_RESOURCE.getCode());

        delete(project);
    }

    private void delete(Project project) {
        projectMapper.delete(project.getId());
    }
}
