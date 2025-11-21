package ru.mkenopsia.projectsservice.dto.project;

import lombok.Builder;
import ru.mkenopsia.projectsservice.dto.SubjectDto;
import ru.mkenopsia.projectsservice.dto.porjectMembers.ProjectMemberDto;
import ru.mkenopsia.projectsservice.dto.task.TaskDto;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ProjectDto(Integer id,
                         String name,
                         String description,
                         LocalDateTime deadline,
                         List<ProjectMemberDto> members,
                         List<TaskDto> tasks,
                         SubjectDto subject) {
}
