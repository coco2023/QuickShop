package com.umistore.imsys.security.controller;

import com.umistore.imsys.security.entity.Permission;
import com.umistore.imsys.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/permission")
public class PermissionController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role/{roleId}/permission")
    public ResponseEntity<?> addPermissionToRole(@PathVariable Integer roleId, @RequestBody Permission permission) {
        roleService.addPermissionToRole(roleId, permission);
        return ResponseEntity.ok().build();
    }

}
