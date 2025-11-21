package ru.mkenopsia.projectsservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mkenopsia.projectsservice.dto.porjectMembers.ProjectMemberDto;
import ru.mkenopsia.projectsservice.dto.project.ProjectCreationRequest;
import ru.mkenopsia.projectsservice.dto.project.ProjectCreationResponse;
import ru.mkenopsia.projectsservice.dto.project.ProjectDto;
import ru.mkenopsia.projectsservice.dto.task.TaskDto;
import ru.mkenopsia.projectsservice.entity.ProjectEntity;
import ru.mkenopsia.projectsservice.entity.ProjectMember;
import ru.mkenopsia.projectsservice.entity.SubjectEntity;
import ru.mkenopsia.projectsservice.entity.TaskEntity;
import ru.mkenopsia.projectsservice.mapper.ProjectMapper;
import ru.mkenopsia.projectsservice.repository.ProjectRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;
    private final SubjectService subjectService;
    private final ProjectMemberService projectMemberService;
    private final TaskService taskService;
    private final ProjectMapper projectMapper;

    @Transactional
    public ProjectCreationResponse create(ProjectCreationRequest request) {
        SubjectEntity subject = subjectService.getSubject(request.subject());
        ProjectEntity projectEntity = projectMapper.toProjectEntity(request, subject);
        repository.save(projectEntity);

        return projectMapper.toResponse(projectEntity);
    }

    @Transactional
    public ProjectDto update(ProjectDto request) {
        SubjectEntity subject = subjectService.getSubject(request.subject().name());
        List<Integer> projectMemberIds = request.members()
                .stream()
                .map(ProjectMemberDto::id)
                .toList();
        List<ProjectMember> projectMembers = projectMemberService.getAllMembersByIds(projectMemberIds);
        List<Integer> tasksIds = request.tasks().stream()
                .map(TaskDto::id)
                .toList();
        List<TaskEntity> tasks = taskService.getAllTasksByIds(tasksIds);
        ProjectEntity entity = projectMapper.toProjectEntity(request, subject, projectMembers, tasks);

        // todo сохранение пользователей

        // todo сохранение заданий

        repository.save(entity);

        return projectMapper.toDto(entity);
    }
}
