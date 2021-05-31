package com.dilsonjlrjr.javatrellodashboardmateus.resource;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.UserDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.service.SecurityResourceService;
import com.dilsonjlrjr.javatrellodashboardmateus.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersResource {

    private static final String ID_USERNAME = "ID_USERNAME";

    private final UserService service;

    private final SecurityResourceService securityResourceService;

    @Autowired
    public UsersResource(UserService service, SecurityResourceService securityResourceService) {
        this.service = service;
        this.securityResourceService = securityResourceService;
    }

    @GetMapping
    public ResponseEntity<PageInfo<UserDtoResponse>> getAll(@PageableDefault Pageable pageable) {
        return ResponseEntity.ok(service.doFindAllUserAndCreateDto(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getId(@PathVariable("id") Long id, @RequestAttribute(ID_USERNAME) Long idToken) {
        securityResourceService.doValidateIdTokenAccessResource(id, idToken);
        return ResponseEntity.ok(service.doFindByIdAndCreateDto(id));
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<PageInfo<ProjectDtoResponse>> getProjects(@PageableDefault Pageable pageable, @PathVariable("id") Long id, @RequestAttribute(ID_USERNAME) Long idToken) {
        securityResourceService.doValidateIdTokenAccessResource(id, idToken);
        return ResponseEntity.ok(service.doFindProjectUserAndCreateDto(pageable, id));
    }
}
