package com.dilsonjlrjr.javatrellodashboardmateus.resource;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.UserDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UsersResource {

    private final UserService service;

    @Autowired
    public UsersResource(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PageInfo<UserDtoResponse>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(service.doFindAllUserAndCreateDto(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.doFindByIdAndCreateDto(id));
    }
}
