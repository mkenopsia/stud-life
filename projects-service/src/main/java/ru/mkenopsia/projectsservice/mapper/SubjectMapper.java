package ru.mkenopsia.projectsservice.mapper;

import org.springframework.stereotype.Component;
import ru.mkenopsia.projectsservice.dto.SubjectDto;
import ru.mkenopsia.projectsservice.entity.SubjectEntity;

@Component
public class SubjectMapper {

    public SubjectDto toSubjectDto(SubjectEntity entity) {
        return new SubjectDto(entity.getName());
    }
}
