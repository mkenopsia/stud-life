package ru.mkenopsia.common.dto;

import lombok.Builder;

@Builder
public record StudentInfoDto(String username,
                             String fio,
                             String email) {
}
