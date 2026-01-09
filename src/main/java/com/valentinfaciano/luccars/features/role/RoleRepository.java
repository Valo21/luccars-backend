package com.valentinfaciano.luccars.features.role;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valentinfaciano.luccars.features.role.entity.Role;
import com.valentinfaciano.luccars.features.role.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
  Optional<Role> findByName(RoleName name);
}
