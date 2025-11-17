package ru.mkenopsia.apisso.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "user_management", name = "t_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Integer id;

    @Column(name = "c_username", nullable = false, unique = true)
    private String username;

    @Column(name = "c_email", nullable = false, unique = true)
    private String email;

    @Column(name = "c_password", nullable = false)
    private String password;

    @Column(name = "c_role", nullable = false)
    private String role;
}
