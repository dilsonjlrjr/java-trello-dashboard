package com.dilsonjlrjr.javatrellodashboardmateus.service;

import com.dilsonjlrjr.javatrellodashboardmateus.mapper.ProjectListsMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.ProjectLists;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectListsService {

    private final ProjectListsMapper projectListsMapper;

    public ProjectListsService(ProjectListsMapper projectListsMapper) {
        this.projectListsMapper = projectListsMapper;
    }

    public List<ProjectLists> getAllByProject(Long idProject) {
        return projectListsMapper.getAllByProject(idProject);
    }
}
