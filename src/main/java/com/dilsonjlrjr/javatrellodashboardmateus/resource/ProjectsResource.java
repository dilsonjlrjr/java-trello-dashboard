package com.dilsonjlrjr.javatrellodashboardmateus.resource;

import com.dilsonjlrjr.javatrellodashboardmateus.model.dto.request.ProjectDtoRequest;
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
    public void update(@PathVariable("id") Long id, @RequestBody @Valid ProjectDtoRequest project, @RequestAttribute(ID_USERNAME) Long idUsername) {
        projectService.doCreateProjectAndUpdate(project, id, idUsername);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Long id, @RequestAttribute(ID_USERNAME) Long idUsername) {
        projectService.doFindProjectAndDelete(id, idUsername);
    }
}
