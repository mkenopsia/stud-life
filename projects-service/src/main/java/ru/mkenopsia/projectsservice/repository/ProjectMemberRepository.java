package ru.mkenopsia.projectsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mkenopsia.projectsservice.entity.ProjectMember;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Integer> {
}
