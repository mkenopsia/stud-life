package ru.mkenopsia.projectsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mkenopsia.projectsservice.entity.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
    SubjectEntity findByName(String name);
}
