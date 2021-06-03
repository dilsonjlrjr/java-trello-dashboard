package com.dilsonjlrjr.javatrellodashboardmateus.resource;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ListsTypeDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.service.ListsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lists/type")
public class ListsTypeResource {

    private final ListsTypeService listsTypeService;

    @Autowired
    public ListsTypeResource(ListsTypeService listsTypeService) {
        this.listsTypeService = listsTypeService;
    }

    @GetMapping
    public ResponseEntity<List<ListsTypeDtoResponse>> getAllListsType() {
        return ResponseEntity.ok(listsTypeService.doGetAllAndCreateDto());
    }
}
