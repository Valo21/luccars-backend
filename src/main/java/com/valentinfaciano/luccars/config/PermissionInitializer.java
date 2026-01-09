package com.valentinfaciano.luccars.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.valentinfaciano.luccars.features.permission.PermissionRepository;
import com.valentinfaciano.luccars.features.permission.entity.Permission;
import com.valentinfaciano.luccars.features.permission.enums.PermissionName;

import lombok.RequiredArgsConstructor;

@Component
@Order(1)
@RequiredArgsConstructor
public class PermissionInitializer implements CommandLineRunner {

    private final PermissionRepository permissionRepository;

    @Override
    public void run(String... args) throws Exception {
        for(PermissionName p : PermissionName.values()) {
            if(permissionRepository.findByName(p).isEmpty()) {
                permissionRepository.save(Permission.builder().name(p).build());
            }
        }
        System.out.println("Permissions initialized");
    }
}
