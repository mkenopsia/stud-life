package ru.mkenopsia.projectsservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mkenopsia.projectsservice.dto.task.TaskDto;
import ru.mkenopsia.projectsservice.entity.TaskEntity;
import ru.mkenopsia.projectsservice.mapper.TaskMapper;
import ru.mkenopsia.projectsservice.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository repository;
    private final TaskMapper taskMapper;

//    public TaskCreationResponse create(TaskDto request) {
//
//    }

    public List<TaskEntity> getAllTasksByIds(List<Integer> tasksIds) {
        return repository.findAllById(tasksIds);
    }
}
