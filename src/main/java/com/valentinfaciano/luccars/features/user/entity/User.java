package com.valentinfaciano.luccars.features.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.valentinfaciano.luccars.features.role.entity.Role;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = true)
  private String password;

  @Column(nullable = false, columnDefinition = "boolean DEFAULT false")
  @Builder.Default
  private Boolean emailVerified = false;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
  @Builder.Default
  private Set<Role> roles = new HashSet<>();

  @CreationTimestamp()
  private Date createdAt;

  @UpdateTimestamp
  private Date updatedAt;
}