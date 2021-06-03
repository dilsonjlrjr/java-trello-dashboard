package com.dilsonjlrjr.javatrellodashboardmateus.resource;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.ProjectDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.ProjectListsDtoRequest;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.SprintDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.response.ProjectListsDtoResponse;
import com.dilsonjlrjr.javatrellodashboardmateus.service.ProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectsResource {

    private static final String ID_USERNAME = "ID_USERNAME";

    private final ProjectService projectService;

    @Autowired
    public ProjectsResource(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping(value = "/{idProject}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDtoResponse> getById(@PathVariable("idProject") Long idProject, @RequestAttribute(ID_USERNAME) Long idUsername) {
        return ResponseEntity.ok(projectService.doFindProjectAndCreateDto(idProject, idUsername));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Void> save(@RequestBody @Valid ProjectDtoRequest project, @RequestAttribute(ID_USERNAME) Long idUsername) {
        Long idProjectInserted = projectService.doCreateProjectAndSave(idUsername, project);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{idProject}").buildAndExpand(idProjectInserted).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, String.valueOf(location)).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{idProject}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public void update(@PathVariable("idProject") Long idProject, @RequestBody @Valid ProjectDtoRequest projectDtoRequest, @RequestAttribute(ID_USERNAME) Long idUsername) {
        projectService.doCreateProjectAndUpdate(projectDtoRequest, idProject, idUsername);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{idProject}")
    @Transactional
    public void delete(@PathVariable("idProject") Long idProject, @RequestAttribute(ID_USERNAME) Long idUsername) {
        projectService.doFindProjectAndDelete(idProject, idUsername);
    }

    @GetMapping(value = "/{idProject}/lists", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectListsDtoResponse>> getLists(@PathVariable("idProject") Long idProject, @RequestAttribute(ID_USERNAME) Long idUsername) {
        return ResponseEntity.ok(projectService.doFindProjectAndGetAllList(idProject, idUsername));
    }

    @PostMapping(value = "/{idProject}/lists", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Transactional
    public ResponseEntity<Void> saveLists(@PathVariable("idProject") Long idProject, @RequestAttribute(ID_USERNAME) Long idUsername,
                                          @RequestBody ProjectListsDtoRequest projectListsDtoRequest) {
        projectService.doFindProjectAndCreateProjectLists(idProject, idUsername, projectListsDtoRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}/lists").buildAndExpand(idProject).toUri();
        return ResponseEntity.status(HttpStatus.CREATED).header(HttpHeaders.LOCATION, location.toString()).build();
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{idProject}/lists/{idList}")
    @Transactional
    public void deleteLists(@PathVariable("idProject") Long idProject, @PathVariable("idList") Integer idList,
                            @RequestAttribute(ID_USERNAME) Long idUsername) {
        projectService.doFindProjectAndDeleteLists(idProject, idUsername, idList);
    }

    @GetMapping(value = "/{idProject}/sprints", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageInfo<SprintDtoResponse>> getAllSprints(@PathVariable("idProject") Long idProject,
                                                                     @RequestAttribute(ID_USERNAME) Long idUsername,
                                                                     @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(projectService.doFindProjectAndGetAllSprints(idProject, idUsername, pageable));
    }

    @GetMapping(value = "/{idProject}/sprints/{idSprint}")
    public ResponseEntity<SprintDtoResponse> getSprintById(@PathVariable("idProject") Long idProject,
                                                           @PathVariable("idSprint") Long idSprint,
                                                           @RequestAttribute(ID_USERNAME) Long idUsername) {
        return ResponseEntity.ok(projectService.doFindProjectAndCreateSprintDtoResponse(idProject, idUsername, idSprint));
    }
}
