package com.umistore.imsys.security.service;

import com.umistore.imsys.security.dto.PermissionDTO;
import com.umistore.imsys.security.dto.RolePermissionDto;
import com.umistore.imsys.security.entity.Permission;
import com.umistore.imsys.security.entity.RolePermission;
import com.umistore.imsys.security.respository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<String> getPermissionsByRoleNames(List<String> roleNames) {
        return permissionRepository.findPermissionsByRoleNames(roleNames).stream()
                .collect(Collectors.toList());
    }

    public List<Permission> findAllPermissions() {
        return permissionRepository.findAll();
    }

    public Permission findPermissionById(Integer id) {
        return permissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
    }

    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    public Permission updatePermission(Integer permissionID, PermissionDTO permissionDTO) {
        Permission permission = permissionRepository.findById(permissionID)
                .orElseThrow(() -> new RuntimeException("Permission not found"));

        if (permissionDTO.getPermissionName() != null) {
            permission.setPermissionName(permissionDTO.getPermissionName());
        }

        if (permissionDTO.getDescription() != null) {
            permission.setPermissionName(permissionDTO.getDescription());
        }

        return permissionRepository.save(permission);
    }

    public void deletePermission(Integer permissionID) {
        Permission permission = permissionRepository.findById(permissionID)
                .orElseThrow(() -> new RuntimeException("Permission not found"));
        permissionRepository.delete(permission);
    }

    public List<Permission> searchPermissionsByName(String name) {
        return permissionRepository.searchByName(name);
    }

}
