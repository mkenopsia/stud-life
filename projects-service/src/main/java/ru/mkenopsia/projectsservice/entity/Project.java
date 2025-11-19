package ru.mkenopsia.projectsservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "project_management", name = "t_project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_name")
    private String name;

    @Column(name = "c_description")
    private String description;

    @Column(name = "c_deadline")
    private LocalDateTime deadline;

    @OneToMany(mappedBy = "project")
    private List<ProjectMember> projectMembers;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;
}
