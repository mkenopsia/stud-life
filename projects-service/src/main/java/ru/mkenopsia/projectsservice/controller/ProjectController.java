package ru.mkenopsia.projectsservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mkenopsia.projectsservice.dto.project.ProjectCreationRequest;
import ru.mkenopsia.projectsservice.dto.task.TaskCreationRequest;
import ru.mkenopsia.projectsservice.service.ProjectService;
import ru.mkenopsia.projectsservice.service.TaskService;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createProject(@RequestBody ProjectCreationRequest request) {
        var response = projectService.create(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/tasks")
    public ResponseEntity<?> addTask(@RequestBody TaskCreationRequest request) {
        var response = taskService.create(request);
    }
}
