package com.umistore.imsys.security.respository;

import com.umistore.imsys.security.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(String roleName);

    Optional<Role> findByRoleID(Integer roleID);
}
