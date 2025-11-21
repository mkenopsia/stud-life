package ru.mkenopsia.projectsservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.mkenopsia.projectsservice.dto.project.ProjectCreationRequest;
import ru.mkenopsia.projectsservice.dto.project.ProjectCreationResponse;
import ru.mkenopsia.projectsservice.dto.project.ProjectDto;
import ru.mkenopsia.projectsservice.entity.ProjectEntity;
import ru.mkenopsia.projectsservice.entity.ProjectMember;
import ru.mkenopsia.projectsservice.entity.SubjectEntity;
import ru.mkenopsia.projectsservice.entity.TaskEntity;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectMapper {

    private final SubjectMapper subjectMapper;
    private final ProjectMemberMapper projectMemberMapper;
    private final TaskMapper taskMapper;

    public ProjectEntity toProjectEntity(ProjectCreationRequest request, SubjectEntity subject) {
        return ProjectEntity.builder()
                .name(request.name())
                .description(request.description())
                .deadline(request.deadline())
                .subject(subject)
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

    public ProjectEntity toProjectEntity(ProjectDto request,
                                         SubjectEntity subject,
                                         List<ProjectMember> projectMembers,
                                         List<TaskEntity> tasks) {
        return ProjectEntity.builder()
                .id(request.id())
                .name(request.name())
                .description(request.description())
                .deadline(request.deadline())
                .projectMembers(projectMembers)
                .taskEntities(tasks)
                .subject(subject)
                .build();
    }

    public ProjectDto toDto(ProjectEntity entity) {
        return ProjectDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .deadline(entity.getDeadline())
                .subject(subjectMapper.toSubjectDto(entity.getSubject()))
                .members(entity.getProjectMembers().stream().map(projectMemberMapper::toDto).toList())
                .tasks(entity.getTaskEntities().stream().map(taskMapper::toDto).toList())
                .build();
    }
}
