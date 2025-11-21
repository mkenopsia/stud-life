package ru.mkenopsia.projectsservice.mapper;

import org.springframework.stereotype.Component;
import ru.mkenopsia.projectsservice.dto.task.TaskDto;
import ru.mkenopsia.projectsservice.entity.TaskEntity;
import ru.mkenopsia.projectsservice.entity.TaskStatus;

@Component
public class TaskMapper {

    public TaskEntity toEntity(TaskDto request) {
        return TaskEntity.builder()
                .name(request.name())
                .description(request.description())
                .deadline(request.deadline())
                .taskStatus(TaskStatus.valueOf(request.status()))
                .build();
    }

    public TaskDto toDto(TaskEntity entity) {
        return TaskDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .deadline(entity.getDeadline())
                .status(entity.getTaskStatus().name())
                .build();
    }
}
