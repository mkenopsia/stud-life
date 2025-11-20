package ru.mkenopsia.projectsservice.dto.task;

import java.time.LocalDateTime;

public record TaskCreationRequest(String name,
                                  String description,
                                  LocalDateTime deadline,
                                  String status) {
}
