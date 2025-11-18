package ru.mkenopsia.projectsservice.mapper;

import org.springframework.stereotype.Component;
import ru.mkenopsia.common.dto.StudentInfoDto;
import ru.mkenopsia.projectsservice.entity.Student;

@Component
public class StudentMapper {

    public Student toEntity(StudentInfoDto dto) {
        return Student.builder()
                .username(dto.username())
                .fio(dto.fio())
                .email(dto.email())
                .build();
    }
}
