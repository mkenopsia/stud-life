package ru.mkenopsia.projectsservice.mapper;

import org.springframework.stereotype.Component;
import ru.mkenopsia.projectsservice.dto.task.TaskCreationRequest;
import ru.mkenopsia.projectsservice.entity.TaskEntity;
import ru.mkenopsia.projectsservice.entity.TaskStatus;

@Component
public class TaskMapper {

    public TaskEntity toEntity(TaskCreationRequest request) {
        return TaskEntity.builder()
                .name(request.name())
                .description(request.description())
                .deadline(request.deadline())
                .taskStatus(TaskStatus.valueOf(request.status()))
                .build();
    }
}
