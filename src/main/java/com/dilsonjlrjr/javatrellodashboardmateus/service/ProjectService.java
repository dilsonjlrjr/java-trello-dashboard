package com.dilsonjlrjr.javatrellodashboardmateus.service;

import com.dilsonjlrjr.javatrellodashboardmateus.exception.EntityNotFoundException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.SecurityResourceException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumProjectServiceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumSecurityResourceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumProjectServiceMessage;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumSecurityResourceMessage;
import com.dilsonjlrjr.javatrellodashboardmateus.mapper.ProjectMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper.ProjectDtoMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper.ProjectListsDtoMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper.SprintDtoMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.ProjectDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.ProjectListsDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.SprintDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectListsDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.SprintDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Project;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.ProjectLists;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Sprint;
import com.dilsonjlrjr.javatrellodashboardmateus.util.DatabaseOrderUtils;
import com.github.pagehelper.PageInfo;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.github.pagehelper.page.PageMethod.startPage;

@Service
public class ProjectService {

    private final ProjectMapper projectMapper;

    private final ProjectListsService projectListsService;

    private final SprintService sprintService;

    private final DatabaseOrderUtils databaseOrderUtils;

    @Autowired
    public ProjectService(ProjectMapper projectMapper,
                          ProjectListsService projectListsService,
                          SprintService sprintService,
                          DatabaseOrderUtils databaseOrderUtils) {
        this.projectMapper = projectMapper;
        this.projectListsService = projectListsService;
        this.sprintService = sprintService;
        this.databaseOrderUtils = databaseOrderUtils;
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

        checkIsOwnerProject(projectWillUpdate, idUsername);

        Project project = Mappers.getMapper(ProjectDtoMapper.class).projectUpdateToProject(projectWillUpdate, projectDtoRequest);

        update(project);
    }

    private void update(Project project) {
        projectMapper.update(project);
    }

    public void doFindProjectAndDelete(Long idProject, Long idUsername) {
        Project project = getById(idProject);

        checkIsOwnerProject(project, idUsername);

        delete(project);
    }

    private void delete(Project project) {
        projectMapper.delete(project.getId());
    }

    public List<ProjectListsDtoResponse> doFindProjectAndGetAllList(Long idProject, Long idUsername) {
        Project project = getById(idProject);

        checkIsOwnerProject(project, idUsername);

        List<ProjectLists> projectLists = projectListsService.getAllByProject(idProject);

        return projectLists.parallelStream().map(Mappers.getMapper(ProjectListsDtoMapper.class)::projectListsToProjectListsDtoResponse).collect(Collectors.toList());
    }

    public void checkIsOwnerProject(Project project, Long idUsername) {
        if (!project.getOwner().getId().equals(idUsername))
            throw new SecurityResourceException(EnumSecurityResourceMessage.UNAUTHORIZED_ACCESS_RESOURCE.getMessage(),
                    EnumSecurityResourceCode.UNAUTHORIZED_ACCESS_RESOURCE.getCode());
    }

    public void doFindProjectAndCreateProjectLists(Long idProject, Long idUsername, ProjectListsDtoRequest projectListsDtoRequest) {
        Project project = getById(idProject);

        checkIsOwnerProject(project, idUsername);

        projectListsService.doCreateProjectListAndSave(project, projectListsDtoRequest);
    }

    public void doFindProjectAndDeleteLists(Long idProject, Long idUsername, Integer idList) {
        Project project = getById(idProject);
        checkIsOwnerProject(project, idUsername);

        projectListsService.doFindProjectListAndDelete(project, idList);
    }

    public PageInfo<SprintDtoResponse> doFindProjectAndGetAllSprints(Long idProject, Long idUsername, Pageable pageable) {
        Project project = getById(idProject);
        checkIsOwnerProject(project, idUsername);

        String orderByDatabase = databaseOrderUtils.doCreateStringOrdebyDatabase(pageable, Sprint.class);
        startPage(pageable.getPageNumber(), pageable.getPageSize(), orderByDatabase);

        List<Sprint> sprints = sprintService.getAll(idProject);

        return new PageInfo<>(sprints.parallelStream().map(Mappers.getMapper(SprintDtoMapper.class)::sprintToSprintDtoResponse).collect(Collectors.toList()));
    }

    public SprintDtoResponse doFindProjectAndCreateSprintDtoResponse(Long idProject, Long idUsername, Long idSprint) {
        Project project = getById(idProject);
        checkIsOwnerProject(project, idUsername);

        return Mappers.getMapper(SprintDtoMapper.class).sprintToSprintDtoResponse(sprintService.getById(idProject, idSprint));
    }

    public Long doFindProjectAndCreateSprint(SprintDtoRequest sprintDtoRequest, Long idProject, Long idUsername) {
        Project project = getById(idProject);
        checkIsOwnerProject(project, idUsername);

        return sprintService.doCreateSprintAndSave(sprintDtoRequest, idProject);
    }

    public void doFindProjectAndUpdateSprint(Long idProject, Long idSprint, Long idUsername, SprintDtoRequest sprintDtoRequest) {
        Project project = getById(idProject);
        checkIsOwnerProject(project, idUsername);

        sprintService.doFindSprintAndUpdate(project.getId(), idSprint, sprintDtoRequest);
    }

    public void doFindProjectAndDeleteSprint(Long idProject, Long idSprint, Long idUsername) {
        Project project = getById(idProject);
        checkIsOwnerProject(project, idUsername);

        sprintService.doFindSprintAndDelete(project.getId(), idSprint);
    }
}
