package ru.mkenopsia.projectsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mkenopsia.projectsservice.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
}
