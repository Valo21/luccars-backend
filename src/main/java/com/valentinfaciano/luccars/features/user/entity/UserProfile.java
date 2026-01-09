package com.valentinfaciano.luccars.features.user.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity()
@Table(name = "user_profiles")
@Data
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;
}