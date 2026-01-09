package com.valentinfaciano.luccars.features.permission.entity;

import java.util.UUID;

import com.valentinfaciano.luccars.features.permission.enums.PermissionName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Permission {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false, unique = true)
  @Enumerated(EnumType.STRING)
  private PermissionName name;
}
