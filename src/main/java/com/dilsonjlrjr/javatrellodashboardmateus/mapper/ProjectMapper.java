package com.dilsonjlrjr.javatrellodashboardmateus.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Project;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {

    List<Project> findByUserId(@Param("id") Long id);
}
