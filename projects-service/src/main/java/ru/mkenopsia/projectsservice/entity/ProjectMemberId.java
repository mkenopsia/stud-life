package ru.mkenopsia.projectsservice.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ProjectMemberId implements Serializable {
    @OneToOne
    @JoinColumn(name = "c_student", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "c_project", referencedColumnName = "id")
    private Project project;
}
