package ru.mkenopsia.projectsservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mkenopsia.projectsservice.entity.ProjectMember;
import ru.mkenopsia.projectsservice.repository.ProjectMemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectMemberService {

    private final ProjectMemberRepository repository;

    public List<ProjectMember> getAllMembersByIds(List<Integer> ids) {
        return repository.findAllById(ids);
    }
}
