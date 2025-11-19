package ru.mkenopsia.projectsservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "project_management", name = "t_task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_name", nullable = false)
    private String name;

    @Column(name = "c_description", nullable = false)
    private String description;

    @Column(name = "c_deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "c_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @OneToMany(
            mappedBy = "parentTask",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Task> requiredTasks;

    @ManyToOne
    @JoinColumn(name = "c_parent_task_id")
    private Task parentTask;

    @ManyToOne
    private Project project;
}
