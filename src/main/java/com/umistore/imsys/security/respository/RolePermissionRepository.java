package com.umistore.imsys.security.respository;

import com.umistore.imsys.security.entity.Permission;
import com.umistore.imsys.security.entity.Role;
import com.umistore.imsys.security.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {
    List<RolePermission> findByRoleID(Role role);

    Optional<Permission> findByPermissionID(Integer permissionID);
}
