package com.valentinfaciano.luccars.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.valentinfaciano.luccars.exceptions.RoleNotFound;
import com.valentinfaciano.luccars.features.permission.PermissionRepository;
import com.valentinfaciano.luccars.features.permission.entity.Permission;
import com.valentinfaciano.luccars.features.permission.enums.PermissionName;
import com.valentinfaciano.luccars.features.role.RoleRepository;
import com.valentinfaciano.luccars.features.role.entity.Role;
import com.valentinfaciano.luccars.features.role.enums.RoleName;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RolePermissionInitializer implements CommandLineRunner {

  private final RoleRepository roleRepository;
  private final PermissionRepository permissionRepository;

  @Override
  public void run(String... args) throws Exception {

    Map<RoleName, List<PermissionName>> rolePermissionsMap = new HashMap<>();

    rolePermissionsMap.put(RoleName.USER, List.of(
        PermissionName.PRODUCT_VIEW));

    List<PermissionName> adminPermissions = new ArrayList<>(rolePermissionsMap.get(RoleName.USER));
    adminPermissions.addAll(List.of(
        PermissionName.PRODUCT_CREATE,
        PermissionName.PRODUCT_EDIT,
        PermissionName.PRODUCT_DELETE,
        PermissionName.USER_VIEW,
        PermissionName.USER_MANAGE));
    rolePermissionsMap.put(RoleName.ADMIN, adminPermissions);

    List<PermissionName> superUserPermissions = new ArrayList<>(rolePermissionsMap.get(RoleName.ADMIN));
    superUserPermissions.addAll(List.of(
    //
    ));
    rolePermissionsMap.put(RoleName.SUPER_USER, superUserPermissions);

    for (Map.Entry<RoleName, List<PermissionName>> entry : rolePermissionsMap.entrySet()) {
      RoleName roleName = entry.getKey();
      List<PermissionName> permissionsForRole = entry.getValue();

      Role role = roleRepository.findByName(roleName)
          .orElseThrow(() -> new RoleNotFound("Role " + roleName + " not found"));

      for (PermissionName permissionName : permissionsForRole) {
        Permission permission = permissionRepository.findByName(permissionName)
            .orElseThrow(() -> new RuntimeException("Permission " + permissionName + " not found")); // TODO: mejorar
                                                                                                     // esto
        role.getPermissions().add(permission);
      }

      roleRepository.save(role);
    }

    System.out.println("Roles and permissions initialized in an organized way");
  }
}
