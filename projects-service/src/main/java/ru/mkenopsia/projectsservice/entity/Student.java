package ru.mkenopsia.projectsservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "student_management", name = "t_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_username", nullable = false, unique = true)
    private String username;

    @Column(name = "c_fio", nullable = false)
    private String fio;

    @Column(name = "c_email", nullable = false, unique = true)
    private String email;
}
