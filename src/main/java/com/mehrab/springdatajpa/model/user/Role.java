package com.mehrab.springdatajpa.model.user;

import jakarta.persistence.*;

@Entity(name = "Role")
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName name;

    public Role() {
    }

    public Role(RoleName name) {
        this.name = name;
    }
}
