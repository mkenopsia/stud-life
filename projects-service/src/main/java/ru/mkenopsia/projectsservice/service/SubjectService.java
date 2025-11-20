package ru.mkenopsia.projectsservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mkenopsia.projectsservice.dto.SubjectDto;
import ru.mkenopsia.projectsservice.entity.SubjectEntity;
import ru.mkenopsia.projectsservice.repository.SubjectRepository;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository repository;

    @Transactional(readOnly = true)
    public SubjectEntity getSubject(String name) {
        return repository.findByName(name);
    }
}
