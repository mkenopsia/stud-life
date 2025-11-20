package ru.mkenopsia.projectsservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mkenopsia.projectsservice.dto.project.ProjectCreationRequest;
import ru.mkenopsia.projectsservice.dto.project.ProjectCreationResponse;
import ru.mkenopsia.projectsservice.entity.ProjectEntity;
import ru.mkenopsia.projectsservice.entity.SubjectEntity;
import ru.mkenopsia.projectsservice.mapper.ProjectMapper;
import ru.mkenopsia.projectsservice.repository.ProjectRepository;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository repository;
    private final SubjectService subjectService;
    private final ProjectMapper projectMapper;

    public ProjectCreationResponse create(ProjectCreationRequest request) {
        SubjectEntity subject = subjectService.getSubject(request.subject());
        ProjectEntity projectEntity = projectMapper.toProjectEntity(request);
        projectEntity.setSubject(subject);
        repository.save(projectEntity);

        return projectMapper.toResponse(projectEntity);
    }
}
