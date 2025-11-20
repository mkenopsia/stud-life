package ru.mkenopsia.projectsservice.dto.project;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ProjectCreationResponse(String name,
                                      String description,
                                      LocalDateTime deadline,
                                      String subject) {
}
