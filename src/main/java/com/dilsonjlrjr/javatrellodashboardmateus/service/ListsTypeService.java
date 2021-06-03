package com.dilsonjlrjr.javatrellodashboardmateus.service;

import com.dilsonjlrjr.javatrellodashboardmateus.mapper.ListsTypeMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper.ListsTypeDtoMapper;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ListsTypeDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.ListsType;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListsTypeService {

    private final ListsTypeMapper listsTypeMapper;

    @Autowired
    public ListsTypeService(ListsTypeMapper listsTypeMapper) {
        this.listsTypeMapper = listsTypeMapper;
    }

    public List<ListsType> getAll() {
        return listsTypeMapper.getAll();
    }

    public List<ListsTypeDtoResponse> doGetAllAndCreateDto() {
        return getAll().parallelStream().map(Mappers.getMapper(ListsTypeDtoMapper.class)::listsTypeToListsTypeDtoREsponse)
                .collect(Collectors.toList());
    }
}
