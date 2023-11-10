package com.umistore.imsys.security.service;

import com.umistore.imsys.security.entity.Permission;
import com.umistore.imsys.security.entity.Role;
import com.umistore.imsys.security.entity.RolePermission;
import com.umistore.imsys.security.respository.PermissionRepository;
import com.umistore.imsys.security.respository.RolePermissionRepository;
import com.umistore.imsys.security.respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    public void addPermissionToRole(Integer roleId, Permission permission) {

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new IllegalArgumentException("Role not found"));

        Permission perm = permissionRepository.findByDescription(permission.getDescription())
                .orElseGet(() -> permissionRepository.save(permission));

        RolePermission rolePermission = RolePermission.builder()
                .roleID(roleId)
                .permissionID(permission.getPermissionID())
                .build();

        rolePermissionRepository.save(rolePermission);
    }

    public List<String> getRoleNamesByUsername(String username) {
        // Assuming you have a method in RoleRepository to get roles by username
        return roleRepository.findRoleNamesByUsername(username).stream()
                .collect(Collectors.toList());
    }


}
