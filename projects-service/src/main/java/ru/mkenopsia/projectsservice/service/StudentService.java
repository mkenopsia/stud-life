package ru.mkenopsia.projectsservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mkenopsia.common.dto.StudentInfoDto;
import ru.mkenopsia.projectsservice.entity.Student;
import ru.mkenopsia.projectsservice.mapper.StudentMapper;
import ru.mkenopsia.projectsservice.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Transactional
    public void save(StudentInfoDto studentInfoDto) {
        Student student = studentMapper.toEntity(studentInfoDto);
        studentRepository.save(student);
    }
}
