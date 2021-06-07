package com.dilsonjlrjr.javatrellodashboardmateus.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.Sprint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface SprintMapper {

    List<Sprint> getAll(@Param("idProject") Long idProject);

    Optional<Sprint> getById(@Param("idProject") Long idProject, @Param("idSprint") Long idSprint);

    void save(@Param("sprint") Sprint sprint);

    void update(@Param("sprint") Sprint sprint);

    void delete(@Param("sprint") Sprint sprint);
}
