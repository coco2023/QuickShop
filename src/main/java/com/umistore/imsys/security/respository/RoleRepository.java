package com.umistore.imsys.security.respository;

import com.umistore.imsys.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String roleName);

    Optional<Role> findByRoleID(Integer roleID);

    @Query("SELECT r.roleName " +
            "FROM Role r " +
            "JOIN UserRole ur ON r.roleID = ur.roleID " +
            "JOIN User u ON ur.userID = u.userID " +
            "WHERE u.username = :username")
    List<String> findRoleNamesByUsername(@Param("username") String username);
}
