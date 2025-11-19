package ru.mkenopsia.projectsservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "project_management", name = "t_project_member")
public class ProjectMember {
    @EmbeddedId
    private ProjectMemberId id;

    @OneToOne
    private Student student;

    @ManyToOne
    private Project project;

    @ManyToMany
    @JoinTable(
            schema = "project_management",
            name = "t_project_member_project_role",
            joinColumns = @JoinColumn(name = "c_project_member"),
            inverseJoinColumns = @JoinColumn(name = "c_project_role")
    )
    private List<ProjectRole> roles;
}
