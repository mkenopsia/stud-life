package ru.mkenopsia.projectsservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "project_management", name = "t_task")
public class TaskEntity {

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
            mappedBy = "parentTaskEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TaskEntity> requiredTaskEntities;

    @ManyToOne
    @JoinColumn(name = "c_parent_task_id")
    private TaskEntity parentTaskEntity;

    @ManyToOne
    private ProjectEntity project;
}
