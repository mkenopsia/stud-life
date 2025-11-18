package ru.mkenopsia.projectsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mkenopsia.projectsservice.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
