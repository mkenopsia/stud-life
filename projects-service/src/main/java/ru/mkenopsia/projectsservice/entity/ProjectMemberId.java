package ru.mkenopsia.projectsservice.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class ProjectMemberId implements Serializable {
    private Integer studentId;
    private Integer projectId;
}
