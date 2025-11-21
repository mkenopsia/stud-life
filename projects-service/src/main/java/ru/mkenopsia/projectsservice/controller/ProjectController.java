package ru.mkenopsia.projectsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mkenopsia.projectsservice.dto.project.ProjectCreationRequest;
import ru.mkenopsia.projectsservice.dto.project.ProjectDto;
import ru.mkenopsia.projectsservice.service.ProjectService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectCreationRequest request) {
        var response = projectService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping
    public ResponseEntity<?> updateProjectData(@RequestBody ProjectDto request) {
        var response = projectService.update(request);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
