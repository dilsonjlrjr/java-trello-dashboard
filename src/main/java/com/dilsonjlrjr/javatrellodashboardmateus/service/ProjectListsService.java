package com.dilsonjlrjr.javatrellodashboardmateus.service;

import com.dilsonjlrjr.javatrellodashboardmateus.exception.EntityNotFoundException;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.code.EnumProjectListsServiceCode;
import com.dilsonjlrjr.javatrellodashboardmateus.exception.message.EnumProjectListsServiceMessage;
import com.dilsonjlrjr.javatrellodashboardmateus.mapper.ProjectListsMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper.ProjectListsDtoMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.ProjectListsDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Project;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.ProjectLists;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectListsService {

    private final ProjectListsMapper projectListsMapper;

    @Autowired
    public ProjectListsService(ProjectListsMapper projectListsMapper) {
        this.projectListsMapper = projectListsMapper;
    }

    public List<ProjectLists> getAllByProject(Long idProject) {
        return projectListsMapper.getAllByProject(idProject);
    }

    public void doCreateProjectListAndSave(Project project, ProjectListsDtoRequest projectListsDtoRequest) {
        ProjectLists projectLists = Mappers.getMapper(ProjectListsDtoMapper.class).projectListsDtoRequestToProjectLists(project, projectListsDtoRequest);

        save(projectLists);
    }

    private void save(ProjectLists projectLists) {
        projectListsMapper.save(projectLists);
    }

    public void doFindProjectListAndDelete(Project project, Integer idList) {
        ProjectLists projectLists = getProjectListsByIdProjectAndIdList(project.getId(), idList);

        delete(projectLists);
    }

    private void delete(ProjectLists projectLists) {
        projectListsMapper.delete(projectLists);
    }

    private ProjectLists getProjectListsByIdProjectAndIdList(Long idProject, Integer idList) {
        return projectListsMapper.getProjectListsByIdProjectAndIdList(idProject, idList)
                .orElseThrow(() -> new EntityNotFoundException(
                        EnumProjectListsServiceMessage.ENTITY_NOT_FOUND.getMessage(),
                        EnumProjectListsServiceCode.ENTITY_NOT_FOUND.getCode()));
    }
}
