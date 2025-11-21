package ru.mkenopsia.projectsservice.dto.porjectMembers;

import lombok.Builder;

import java.util.List;

@Builder
public record ProjectMemberDto(Integer id,
                               Integer studentId,
                               Integer projectId,
                               List<String> roles) {
}
