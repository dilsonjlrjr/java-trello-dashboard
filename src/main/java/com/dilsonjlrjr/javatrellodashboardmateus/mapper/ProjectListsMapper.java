package com.dilsonjlrjr.javatrellodashboardmateus.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.ProjectLists;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface ProjectListsMapper {
    List<ProjectLists> getAllByProject(@Param("idProject") Long idProject);

    void save(@Param("projectLists") ProjectLists projectLists);

    Optional<ProjectLists> getProjectListsByIdProjectAndIdList(@Param("idProject") Long idProject, @Param("idList") Integer idList);

    void delete(@Param("projectLists") ProjectLists projectLists);
}
