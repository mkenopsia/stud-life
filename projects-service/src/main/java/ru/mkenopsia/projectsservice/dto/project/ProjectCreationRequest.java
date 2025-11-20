package ru.mkenopsia.projectsservice.dto.project;

import java.time.LocalDateTime;

public record ProjectCreationRequest(String name,
                                     String description,
                                     LocalDateTime deadline,
                                     String subject) {
}
