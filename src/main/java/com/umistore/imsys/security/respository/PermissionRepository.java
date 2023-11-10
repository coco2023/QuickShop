package com.umistore.imsys.security.respository;

import com.umistore.imsys.security.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    Optional<Permission> findByDescription(String description);

    @Query("SELECT p.description " +
            "FROM Permission p " +
            "JOIN RolePermission rp ON p.permissionID = rp.permissionID " +
            "JOIN Role r ON rp.roleID = r.roleID " +
            "WHERE r.roleName IN :roleNames")
    List<String> findPermissionsByRoleNames(@Param("roleNames") List<String> roleNames);
}
