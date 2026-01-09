package com.valentinfaciano.luccars.config;

import com.valentinfaciano.luccars.features.role.RoleRepository;
import com.valentinfaciano.luccars.features.role.entity.Role;
import com.valentinfaciano.luccars.features.role.enums.RoleName;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {

  private final RoleRepository roleRepository;

  @Override
  public void run(String... args) throws Exception {

    if (roleRepository.findByName(RoleName.USER).isEmpty()) {
      roleRepository.save(Role.builder().name(RoleName.USER).build());
      System.out.println("Role USER created");
    }

    if (roleRepository.findByName(RoleName.ADMIN).isEmpty()) {
      roleRepository.save(Role.builder().name(RoleName.ADMIN).build());
      System.out.println("Role ADMIN created");
    }

    if (roleRepository.findByName(RoleName.SUPER_USER).isEmpty()) {
      roleRepository.save(Role.builder().name(RoleName.SUPER_USER).build());
      System.out.println("Role SUPER_USER created");
    }
  }
}