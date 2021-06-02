package com.dilsonjlrjr.javatrellodashboardmateus.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.ProjectLists;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProjectListsMapper {
    List<ProjectLists> getAllByProject(@Param("idProject") Long idProject);

    void save(@Param("projectLists") ProjectLists projectLists);
}
