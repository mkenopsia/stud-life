package ru.mkenopsia.projectsservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mkenopsia.projectsservice.dto.task.TaskCreationRequest;
import ru.mkenopsia.projectsservice.dto.task.TaskCreationResponse;
import ru.mkenopsia.projectsservice.mapper.TaskMapper;
import ru.mkenopsia.projectsservice.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskCreationResponse create(TaskCreationRequest request) {

    }
}
