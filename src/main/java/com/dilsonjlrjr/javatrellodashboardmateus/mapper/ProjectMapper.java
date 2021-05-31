package com.dilsonjlrjr.javatrellodashboardmateus.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface ProjectMapper {

    List<Project> findByUserId(@Param("id") Long id);

    void save(@Param("project") Project project);

    Optional<Project> findById(@Param("id") Long id);

    void update(@Param("project") Project project);

    void delete(@Param("id") Long id);
}
