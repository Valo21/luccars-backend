package com.valentinfaciano.luccars.features.permission;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valentinfaciano.luccars.features.permission.entity.Permission;
import com.valentinfaciano.luccars.features.permission.enums.PermissionName;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {
  Optional<Permission> findByName(PermissionName name);
}
