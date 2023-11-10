package com.umistore.imsys.security.service;

import com.umistore.imsys.security.dto.PermissionDTO;
import com.umistore.imsys.security.dto.RoleDTO;
import com.umistore.imsys.security.dto.RolePermissionDto;
import com.umistore.imsys.security.entity.Permission;
import com.umistore.imsys.security.entity.Role;
import com.umistore.imsys.security.entity.RolePermission;
import com.umistore.imsys.security.respository.PermissionRepository;
import com.umistore.imsys.security.respository.RolePermissionRepository;
import com.umistore.imsys.security.respository.RoleRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
public class RolePermissionService {

    @Autowired
    private RolePermissionRepository rolePermissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public RolePermissionDto addOnePermissionToRole(RolePermissionDto rolePermissionDto) {

        // Validate the DTO
        validateRolePermissionDto(rolePermissionDto);

        RolePermission rolePermission = RolePermission.builder()
                .roleID(rolePermissionDto.getRoleId())
                .permissionID(rolePermissionDto.getPermissionId())
                .build();
        rolePermissionRepository.save(rolePermission);
        
        return convertEntityToDto(rolePermission);
    }

    @Transactional
    public void addNewPermissionsToRole(Integer roleId, List<Integer> newPermissionIds) {

        // Fetch the existing permissions for the role
        List<RolePermission> existingRolePermissions = rolePermissionRepository.findByRoleID(roleId);
        Set<Integer> existingPermissionIds = existingRolePermissions.stream()
                .map(RolePermission::getPermissionID)
                .collect(Collectors.toSet());

        // Filter out the new permissions that are already associated with the role
        List<Integer> filteredNewPermissionIds = newPermissionIds.stream()
                .filter(permissionId -> !existingPermissionIds.contains(permissionId))
                .collect(Collectors.toList());
        log.info("***filteredNewPermissionIds: " + filteredNewPermissionIds);

        // Fetch and associate each new permission with the role
        filteredNewPermissionIds.forEach(permissionId -> {
            // check if permission exist
            permissionRepository.findById(permissionId)
                    .orElseThrow(() -> new IllegalArgumentException("Permission not found with id: " + permissionId));
            RolePermission rolePermission = RolePermission.builder()
                    .roleID(roleId)
                    .permissionID(permissionId)
                    .build();
            rolePermissionRepository.save(rolePermission);
        });
    }

    private void validateRolePermissionDto(RolePermissionDto rolePermissionDto) {

        // Check if the role ID is valid and exists
        if (rolePermissionDto.getRoleId() == null || !roleRepository.existsById(rolePermissionDto.getRoleId())) {
            throw new IllegalArgumentException("The role ID provided is invalid or does not exist.");
        }

        // Check if the permission ID is valid and exists
        if (rolePermissionDto.getPermissionId() == null || !permissionRepository.existsById(rolePermissionDto.getPermissionId())) {
            throw new IllegalArgumentException("The permission ID provided is invalid or does not exist.");
        }

        // Check if the permission is already assigned to the role
        if (rolePermissionRepository.existsAllByRoleIDAndPermissionID(
                rolePermissionDto.getRoleId(), rolePermissionDto.getPermissionId())) {
            throw new IllegalArgumentException("The permission is already assigned to this role.");
        }

    }

    public List<RolePermissionDto> getAllRolePermissions() {
        return rolePermissionRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    public List<PermissionDTO> getPermissionsForRole(Integer roleId) {
        return rolePermissionRepository.findByRoleID(roleId).stream()
                .map(rolePermission -> {
                    Integer permissionID = rolePermission.getPermissionID();
                    Permission permission = permissionRepository.findById(permissionID).get();
                    return convertEntityToDto(permission);
                })
                .collect(Collectors.toList());
    }

    public RoleDTO deleteAllAndAssignNew(Integer roleId, List<PermissionDTO> permissionDTOs) {
        // Assuming that permissionDtos contain the updated list of permissions for the role
        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new NoSuchElementException("Role not found with id " + roleId));

        // Clear existing permissions of the role
        rolePermissionRepository.deleteByRoleID(roleId);

        // Assign new permissions
        permissionDTOs.forEach(permissionDto -> {
            permissionRepository.findById(permissionDto.getPermissionID())
                    .orElseThrow(() -> new IllegalArgumentException("Permission not found with id: " + permissionDto.getPermissionID()));
            RolePermission rolePermission = RolePermission.builder()
                            .roleID(roleId)
                            .permissionID(permissionDto.getPermissionID())
                            .build();
            rolePermissionRepository.save(rolePermission);
        });

        return convertEntityToDto(role);
    }

    public void deletePermissionsForRole(Integer roleId) {
        rolePermissionRepository.deleteByRoleID(roleId);
    }

    public List<RoleDTO> getRolesByPermissionName(String permissionName) {
        // find the role IDs associated with the given permission name
        List<Integer> roleIDsList = rolePermissionRepository.findRoleIdsByPermissionName(permissionName);

        // find the roles by the retrieved role IDs
        List<Role> roles = roleRepository.findByRoleIDIn(roleIDsList);

        return roles.stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    private PermissionDTO convertEntityToDto(Permission permission) {
        return PermissionDTO.builder()
                .permissionID(permission.getPermissionID())
                .permissionName(permission.getPermissionName())
                .description(permission.getDescription())
                .build();
    }

    private RolePermissionDto convertEntityToDto(RolePermission rolePermission) {
        return RolePermissionDto.builder()
                .rolePermissionID(rolePermission.getRolePermissionID())
                .roleId(rolePermission.getRoleID())
                .permissionId(rolePermission.getPermissionID())
                .build();
    }

    private RoleDTO convertEntityToDto(Role role) {
        return RoleDTO.builder()
                .roleID(role.getRoleID())
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .build();
    }

}
