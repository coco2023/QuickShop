package com.umistore.imsys.security.controller;

import com.umistore.imsys.security.dto.PermissionDTO;
import com.umistore.imsys.security.entity.Permission;
import com.umistore.imsys.security.respository.PermissionRepository;
import com.umistore.imsys.security.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/create")
    public Permission createPermission(@RequestBody Permission permission) {
        return permissionService.savePermission(permission);
    }

    @GetMapping("/all")
    public List<Permission> getAllPermissions() {
        return permissionService.findAllPermissions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Permission> getPermissionById(@PathVariable Integer id) {
        return ResponseEntity.ok(permissionService.findPermissionById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Permission> updatePermission(@PathVariable Integer id, @Valid @RequestBody PermissionDTO permissionDTO) {
        try {
            Permission updatedPermission = permissionService.updatePermission(id, permissionDTO);
            return ResponseEntity.ok(updatedPermission);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePermission(@PathVariable Integer id) {
        try {
            permissionService.deletePermission(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Permission>> searchPermissions(@RequestParam String name) {
        List<Permission> permissions = permissionService.searchPermissionsByName(name);
        return ResponseEntity.ok(permissions);
    }

}
