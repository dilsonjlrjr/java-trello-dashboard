package com.dilsonjlrjr.javatrellodashboardmateus.model.dto.mapper;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ListsTypeDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.entities.ListsType;
import org.mapstruct.Mapper;

@Mapper
public interface ListsTypeDtoMapper {

    ListsTypeDtoResponse listsTypeToListsTypeDtoREsponse(ListsType listsType);
}
