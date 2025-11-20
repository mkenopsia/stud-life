package ru.mkenopsia.projectsservice.mapper;

import org.springframework.stereotype.Component;
import ru.mkenopsia.projectsservice.dto.project.ProjectCreationRequest;
import ru.mkenopsia.projectsservice.dto.project.ProjectCreationResponse;
import ru.mkenopsia.projectsservice.entity.ProjectEntity;

@Component
public class ProjectMapper {

    public ProjectEntity toProjectEntity(ProjectCreationRequest request) {
        return ProjectEntity.builder()
                .name(request.name())
                .description(request.description())
                .deadline(request.deadline())
                .build();
    }

    public ProjectCreationResponse toResponse(ProjectEntity projectEntity) {
        return ProjectCreationResponse.builder()
                .name(projectEntity.getName())
                .description(projectEntity.getDescription())
                .deadline(projectEntity.getDeadline())
                .subject(projectEntity.getSubject().getName())
                .build();
    }
}
