package com.dilsonjlrjr.javatrellodashboardmateus.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.ListsType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ListsTypeMapper {
    List<ListsType> getAll();
}
