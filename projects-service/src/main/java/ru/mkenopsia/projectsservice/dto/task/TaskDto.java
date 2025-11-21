package ru.mkenopsia.projectsservice.dto.task;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskDto(Integer id,
                      String name,
                      String description,
                      LocalDateTime deadline,
                      String status) {
}
