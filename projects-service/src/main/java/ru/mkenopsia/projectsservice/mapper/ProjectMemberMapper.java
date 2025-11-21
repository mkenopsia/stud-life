package ru.mkenopsia.projectsservice.mapper;

import org.springframework.stereotype.Component;
import ru.mkenopsia.projectsservice.dto.porjectMembers.ProjectMemberDto;
import ru.mkenopsia.projectsservice.entity.ProjectMember;
import ru.mkenopsia.projectsservice.entity.ProjectRole;

@Component
public class ProjectMemberMapper {

    public ProjectMemberDto toDto(ProjectMember member) {
        return ProjectMemberDto.builder()
                .id(member.getId())
                .projectId(member.getProject().getId())
                .studentId(member.getStudent().getId())
                .roles(member.getRoles().stream().map(ProjectRole::getName).toList())
                .build();
    }
}
