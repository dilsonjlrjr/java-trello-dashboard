package com.dilsonjlrjr.javatrellodashboardmateus.service;

import com.dilsonjlrjr.javatrellodashboardmateus.mapper.SprintMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Sprint;
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
}
