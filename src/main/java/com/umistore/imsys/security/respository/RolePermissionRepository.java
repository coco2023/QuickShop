package com.umistore.imsys.security.respository;

import com.umistore.imsys.security.dto.PermissionDTO;
import com.umistore.imsys.security.entity.Permission;
import com.umistore.imsys.security.entity.Role;
import com.umistore.imsys.security.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission, Integer> {
    List<RolePermission> findByRoleID(Integer role);

    Optional<Permission> findByPermissionID(Integer permissionID);

    boolean existsAllByRoleIDAndPermissionID(Integer roleId, Integer permissionId);

    void deleteByRoleID(Integer roleId);

    @Query("SELECT rp.roleID " +
            "FROM RolePermission rp " +
            "JOIN Permission p " +
            "ON rp.permissionID = p.permissionID " +
            "WHERE LOWER(p.permissionName) = LOWER(:permissionName) ")
    List<Integer> findRoleIdsByPermissionName(@Param("permissionName") String permissionName);
}
