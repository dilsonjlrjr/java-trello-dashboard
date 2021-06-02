package com.dilsonjlrjr.javatrellodashboardmateus.resource;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.ProjectDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.ProjectListsDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectListsDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/projects")
public class ProjectsResource {

    private static final String ID_USERNAME = "ID_USERNAME";

    private final ProjectService projectService;

    @Autowired
    public ProjectsResource(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDtoResponse> getById(@PathVariable("id") Long id, @RequestAttribute(ID_USERNAME) Long idUsername) {
        return ResponseEntity.ok(projectService.doFindProjectAndCreateDto(id, idUsername));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Void> save(@RequestBody @Valid ProjectDtoRequest project, @RequestAttribute(ID_USERNAME) Long idUsername) {
        Long idInserted = projectService.doCreateProjectAndSave(idUsername, project);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(idInserted).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void update(@PathVariable("id") Long id, @RequestBody @Valid ProjectDtoRequest project, @RequestAttribute(ID_USERNAME) Long idUsername) {
        projectService.doCreateProjectAndUpdate(project, id, idUsername);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    @Transactional
    public void delete(@PathVariable("id") Long id, @RequestAttribute(ID_USERNAME) Long idUsername) {
        projectService.doFindProjectAndDelete(id, idUsername);
    }

    @GetMapping(value = "/{id}/lists", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectListsDtoResponse>> getLists(@PathVariable("id") Long id, @RequestAttribute(ID_USERNAME) Long idUsername) {
        return ResponseEntity.ok(projectService.doFindProjectAndGetAllList(id, idUsername));
    }

    @PostMapping(value = "/{id}/lists", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Void> saveLists(@PathVariable("id") Long id, @RequestAttribute(ID_USERNAME) Long idUsername,
                                          @RequestBody ProjectListsDtoRequest projectListsDtoRequest) {
        projectService.doFindProjectAndCreateProjectLists(id, idUsername, projectListsDtoRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}/lists").buildAndExpand(id).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, location.toString()).build();
    }

}
