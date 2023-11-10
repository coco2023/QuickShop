package com.umistore.imsys.security.controller;

import com.umistore.imsys.security.dto.PermissionDTO;
import com.umistore.imsys.security.dto.RoleDTO;
import com.umistore.imsys.security.dto.RolePermissionDto;
import com.umistore.imsys.security.service.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/rolepermissions")
@Api(value = "RolePermission Controller", description = "Role-Permission Controller")
public class RolePermissionController {

    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    public ResponseEntity<List<RolePermissionDto>> getAllRolePermissions() {
        List<RolePermissionDto> list = rolePermissionService.getAllRolePermissions();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/assign/dto")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @ApiOperation(value = "assignPermissionToRole", notes = "assign a new permission to the role using RolePermissionDTO")
    public ResponseEntity<RolePermissionDto> addOnePermissionToRole(@RequestBody RolePermissionDto rolePermissionDto) {
        RolePermissionDto newRolePermissionDto = rolePermissionService.addOnePermissionToRole(rolePermissionDto);
        return new ResponseEntity<>(newRolePermissionDto, HttpStatus.CREATED);
    }

    @PatchMapping("/assign/{roleId}/permissions")
    public ResponseEntity<?> addNewPermissionsToRole(@PathVariable Integer roleId, @RequestBody List<Integer> newPermissionIds) {
        rolePermissionService.addNewPermissionsToRole(roleId, newPermissionIds);
        return ResponseEntity.ok("New permissions added to the role successfully.");
    }

    // get permissions of role(by roleId)
    @GetMapping("/{roleId}/all")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @ApiOperation(value = "getPermissionsForRole", notes = "get permissions of role (by roleId)")
    public ResponseEntity<List<PermissionDTO>> getPermissionsForRole(@PathVariable Integer roleId) {
        List<PermissionDTO> permissions = rolePermissionService.getPermissionsForRole(roleId);
        return ResponseEntity.ok(permissions);
    }

    // delete All permissions and assign New permission for role(by roleId)
    @PutMapping("/{roleId}/updateAll")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @ApiOperation(value = "updatePermissionsForRole", notes = "delete All permissions and assign New permission for role(by roleId)")
    public ResponseEntity<RoleDTO> updatePermissionsForRole(@PathVariable Integer roleId, @RequestBody List<PermissionDTO> permissionDTOs) {
        RoleDTO updatedRole = rolePermissionService.deleteAllAndAssignNew(roleId, permissionDTOs);
        return ResponseEntity.ok(updatedRole);
    }

    // delete permissions for role
    @DeleteMapping("/{roleId}/deleteAll")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @ApiOperation(value = "deletePermissionsForRole", notes = "delete all permissions of the role")
    public ResponseEntity<?> deletePermissionsForRole(@PathVariable Integer roleId) {
        rolePermissionService.deletePermissionsForRole(roleId);
        return ResponseEntity.ok().build();
    }

    // search roles by permissionName
    @GetMapping("/{roleId}/search")
    @PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
    @ApiOperation(value = "searchPermissionsForRole", notes = "search roles by permissionName")
    public ResponseEntity<List<RoleDTO>> searchPermissionsForRole(@PathVariable Integer roleId, @RequestParam String permissionName) {
        List<RoleDTO> roles = rolePermissionService.getRolesByPermissionName(permissionName);
        return roles.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(roles);
    }

}
