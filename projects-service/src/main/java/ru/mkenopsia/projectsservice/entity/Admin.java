package ru.mkenopsia.projectsservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(schema = "user_management", name = "t_admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "c_nickname")
    private String nickname;

    @Column(name = "c_email")
    private String email;

    @OneToMany(mappedBy = "admin")
    private List<Subject> subjects;
}
